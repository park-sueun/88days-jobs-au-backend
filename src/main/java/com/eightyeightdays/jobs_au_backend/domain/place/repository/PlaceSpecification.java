package com.eightyeightdays.jobs_au_backend.domain.place.repository;

import com.eightyeightdays.jobs_au_backend.domain.place.entity.Place;
import org.springframework.data.jpa.domain.Specification;

public class PlaceSpecification {

    public static Specification<Place> withinBounds(Double north, Double south, Double east, Double west) {
        double latMin = Math.min(north, south);
        double latMax = Math.max(north, south);
        double lngMin = Math.min(east, west);
        double lngMax = Math.max(east, west);

        return (root, query, cb) -> cb.and(
                cb.isNotNull(root.get("latitude")),
                cb.isNotNull(root.get("longitude")),
                cb.between(root.get("latitude"), latMin, latMax),
                cb.between(root.get("longitude"), lngMin, lngMax)
        );
    }
}
