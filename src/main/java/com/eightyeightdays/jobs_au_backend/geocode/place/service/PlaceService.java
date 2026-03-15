package com.eightyeightdays.jobs_au_backend.geocode.place.service;

import com.eightyeightdays.jobs_au_backend.geocode.place.client.GooglePlacesClient;
import com.eightyeightdays.jobs_au_backend.geocode.place.dto.Address;
import com.eightyeightdays.jobs_au_backend.geocode.place.dto.GooglePlacesResponse;
import com.eightyeightdays.jobs_au_backend.geocode.place.dto.Place;
import com.eightyeightdays.jobs_au_backend.geocode.place.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final GooglePlacesClient googlePlacesClient;

    public PlaceService(GooglePlacesClient client) {
        this.googlePlacesClient = client;
    }

    public List<Place> search(String query) {

        GooglePlacesResponse res =
                googlePlacesClient.searchPlace(query);

        List<Result> result = res.results();

        return result.stream()
                .map(r -> new Place(
                        r.name(),
                        r.geometry(),
                        Address.of(r.formatted_address())
                )).toList();
    }
}
