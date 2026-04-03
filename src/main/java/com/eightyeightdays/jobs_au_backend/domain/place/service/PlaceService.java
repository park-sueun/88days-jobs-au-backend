package com.eightyeightdays.jobs_au_backend.domain.place.service;

import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Place;
import com.eightyeightdays.jobs_au_backend.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceResponse create(PlaceRequest request) {
        Place place = Place.builder()
            .name(request.name())
            .website(request.website())
            .description(request.description())
            .state(request.state())
            .region(request.region())
            .postcode(request.postcode())
            .address(request.address())
            .latitude(request.latitude())
            .longitude(request.longitude())
            .category(request.category())
            .season(request.season())
            .cropType(request.cropType())
            .payRate(request.payRate())
            .isVisaExtensionEligible(request.isVisaExtensionEligible())
            .hasAccommodation(request.hasAccommodation())
            .status(request.status())
            .build();

        placeRepository.save(place);

        return PlaceResponse.from(place);
    }

    public List<PlaceResponse> getAll() {
        return placeRepository.findAll().stream()
                .map(PlaceResponse::from)
                .toList();
    }

    public PlaceResponse get(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Place not found"
                ));
        return PlaceResponse.from(place);
    }

    @Transactional
    public PlaceResponse update(Long id, PlaceRequest request) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Place not found"
                ));
        place.update(request);
        return PlaceResponse.from(place);
    }

    @Transactional
    public PlaceResponse patch(Long id, PlaceRequest request) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Place not found"
                ));
        place.patch(request);
        return PlaceResponse.from(place);
    }

    @Transactional
    public void delete(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Place not found"
                ));
        placeRepository.delete(place);
    }
}