package com.eightyeightdays.jobs_au_backend.geocode.place.controller;

import com.eightyeightdays.jobs_au_backend.geocode.place.dto.Place;
import com.eightyeightdays.jobs_au_backend.geocode.place.service.PlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geocode/places")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Place> search(
            @RequestParam String query
    ) {
        return service.search(query);
    }

}
