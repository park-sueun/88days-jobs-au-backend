package com.eightyeightdays.jobs_au_backend.domain.user.dto;

import com.eightyeightdays.jobs_au_backend.domain.user.dto.profile.ProfileResponse;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.User;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.UserRole;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        String profileImageUrl,
        UserRole role,
        ProfileResponse profile
) {
    public static UserResponse from(User user, ProfileResponse profile) {

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getProfileImageUrl(),
                user.getRole(),
                profile
        );

    }
}
