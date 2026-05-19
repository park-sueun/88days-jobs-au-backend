package com.eightyeightdays.jobs_au_backend.domain.place.service;

import com.eightyeightdays.jobs_au_backend.domain.place.dto.ContactRequest;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceSummaryResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.dto.PlacesResponse;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Contact;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Place;
import com.eightyeightdays.jobs_au_backend.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (request.contacts() != null) {
            place.updateContacts(toContacts(request.contacts(), place));
        }

        return PlaceResponse.from(place);
    }

    // bounds 내 장소 조회 — JOIN FETCH로 N+1 해결, 경량 DTO 반환
    public List<PlaceSummaryResponse> findWithinBounds(Double north, Double south, Double east, Double west) {
        double latMin = Math.min(north, south);
        double latMax = Math.max(north, south);
        double lngMin = Math.min(east, west);
        double lngMax = Math.max(east, west);

        return placeRepository.findWithinBoundsWithContacts(latMin, latMax, lngMin, lngMax)
                .stream()
                .map(PlaceSummaryResponse::from)
                .toList();
    }

    // 페이지네이션 목록 조회
    public Page<PlaceSummaryResponse> getAll(Pageable pageable) {
        return placeRepository.findAllActive(pageable)
                .map(PlaceSummaryResponse::from);
    }

    // 단건 상세 조회 — contacts JOIN FETCH로 N+1 해결
    public PlaceResponse get(Long id) {
        Place place = placeRepository.findByIdWithContacts(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
        return PlaceResponse.from(place);
    }

    @Transactional
    public PlaceResponse update(Long id, PlaceRequest request) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        place.update(request);
        place.updateContacts(
                request.contacts() != null ? toContacts(request.contacts(), place) : List.of()
        );

        return PlaceResponse.from(place);
    }

    @Transactional
    public PlaceResponse patch(Long id, PlaceRequest request) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        place.patch(request);
        if (request.contacts() != null) {
            place.updateContacts(toContacts(request.contacts(), place));
        }

        return PlaceResponse.from(place);
    }

    private boolean hasValue(String s) {
        return s != null && !s.isBlank();
    }

    private List<Contact> toContacts(List<ContactRequest> requests, Place place) {
        return requests.stream()
                .filter(c -> hasValue(c.name()) || hasValue(c.phone()) || hasValue(c.email()))
                .map(c -> Contact.of(c, place))
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
        placeRepository.delete(place);
    }
}
