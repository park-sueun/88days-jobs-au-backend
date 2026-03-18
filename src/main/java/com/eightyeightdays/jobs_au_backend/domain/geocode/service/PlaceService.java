package com.eightyeightdays.jobs_au_backend.domain.geocode.service;

import com.eightyeightdays.jobs_au_backend.domain.company.entity.Company;
import com.eightyeightdays.jobs_au_backend.infra.external.google.GooglePlacesClient;
import com.eightyeightdays.jobs_au_backend.domain.geocode.dto.Address;
import com.eightyeightdays.jobs_au_backend.infra.external.google.dto.GooglePlacesResponse;
import com.eightyeightdays.jobs_au_backend.domain.geocode.dto.Place;
import com.eightyeightdays.jobs_au_backend.domain.geocode.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Place> findByCompany(Company company) {
        List<Place> results = search(company.getName() + company.getSuburb() + company.getState());

        return results.stream()
                .filter(r -> r.address().getState().equals(company.getState().toString()))
                .filter(r -> r.address().getSuburb().equals(company.getSuburb()))
                .findFirst();
    }
}
