package com.eightyeightdays.jobs_au_backend.geocode.place.client;

import com.eightyeightdays.jobs_au_backend.geocode.place.dto.GooglePlacesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GooglePlacesClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${google.places.api.key}")
    private String apiKey;

    @Value("${google.api.base_url}")
    private String baseUrl;

    public GooglePlacesResponse searchPlace(String query) {

        return webClientBuilder
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/maps/api/place/textsearch/json")
                                .queryParam("query", query)
                                .queryParam("region", "au")
                                .queryParam("key", apiKey)
                                .build())
                .retrieve()
                .bodyToMono(GooglePlacesResponse.class)
                .block();
    }
}