package com.eightyeightdays.jobs_au_backend.domain.place.controller;

import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceSummaryResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResponse create(@RequestBody PlaceRequest request) {
        return placeService.create(request);
    }

    // bounds 지정 시: 지도 영역 내 마커 조회 (경량 DTO)
    // bounds 미지정 시: 페이지네이션 목록 조회 (기본 20개)
    @GetMapping
    public Object getAll(
            @RequestParam(required = false) Double north,
            @RequestParam(required = false) Double south,
            @RequestParam(required = false) Double east,
            @RequestParam(required = false) Double west,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        boolean hasBounds = north != null || south != null || east != null || west != null;

        if (hasBounds) {
            if (north == null || south == null || east == null || west == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Provide all of north, south, east, west, or omit all for paginated list");
            }
            List<PlaceSummaryResponse> places = placeService.findWithinBounds(north, south, east, west);
            return new BoundsResult(places, places.size());
        }

        return placeService.getAll(pageable);
    }

    record BoundsResult(List<PlaceSummaryResponse> places, int total) {}

    @GetMapping("/{id}")
    public PlaceResponse get(@PathVariable Long id) {
        return placeService.get(id);
    }

    @PutMapping("/{id}")
    public PlaceResponse update(@PathVariable Long id, @RequestBody PlaceRequest request) {
        return placeService.update(id, request);
    }

    @PatchMapping("/{id}")
    public PlaceResponse patch(@PathVariable Long id, @RequestBody PlaceRequest request) {
        return placeService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        placeService.delete(id);
    }
}