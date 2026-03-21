package com.eightyeightdays.jobs_au_backend.domain.company.repository;

import com.eightyeightdays.jobs_au_backend.domain.company.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.Company;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.State;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {

    public static Specification<Company> hasCategory(Category category) {
        return (root, query, cb) ->
                category == null ? cb.conjunction() : cb.equal(root.get("category"), category);
    }

    public static Specification<Company> hasState(State state) {
        return (root, query, cb) ->
                state == null ? cb.conjunction() : cb.equal(root.get("state"), state);
    }

    public static Specification<Company> hasSeason(String season) {
        return (root, query, cb) ->
                season == null ? cb.conjunction() : cb.equal(root.get("season"), season);
    }

    public static Specification<Company> hasCropType(String cropType) {
        return (root, query, cb) ->
                cropType == null ? cb.conjunction() : cb.equal(root.get("cropType"), cropType);
    }
}