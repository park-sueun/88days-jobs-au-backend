package com.eightyeightdays.jobs_au_backend.domain.place.controller;

import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlacesResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public PlacesResponse getAll(
            @RequestParam(required = false) Double north,
            @RequestParam(required = false) Double south,
            @RequestParam(required = false) Double east,
            @RequestParam(required = false) Double west
    ) {

        if (north == null && south == null && east == null && west == null) {
            List<PlaceResponse> places = placeService.getAll();
            return new PlacesResponse(places, places.size());
        }
        if (north == null || south == null || east == null || west == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Provide all of north, south, east, west, or omit all for full list");
        }
        List<PlaceResponse> places = placeService.findWithinBounds(north, south, east, west);
        return new PlacesResponse(places, places.size());
    }

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