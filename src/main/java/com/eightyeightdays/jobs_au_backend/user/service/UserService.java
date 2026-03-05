package com.eightyeightdays.jobs_au_backend.user.service;

import com.eightyeightdays.jobs_au_backend.user.dto.UserResponse;
import com.eightyeightdays.jobs_au_backend.user.dto.profile.EmployerCompanyProfileResponse;
import com.eightyeightdays.jobs_au_backend.user.dto.profile.EmployerProfileResponse;
import com.eightyeightdays.jobs_au_backend.user.dto.profile.WorkerProfileResponse;
import com.eightyeightdays.jobs_au_backend.user.model.EmployerCompanyProfile;
import com.eightyeightdays.jobs_au_backend.user.model.User;
import com.eightyeightdays.jobs_au_backend.user.model.UserRole;
import com.eightyeightdays.jobs_au_backend.user.model.WorkerProfile;
import com.eightyeightdays.jobs_au_backend.user.repository.EmployerCompanyProfileRepository;
import com.eightyeightdays.jobs_au_backend.user.repository.UserRepository;
import com.eightyeightdays.jobs_au_backend.user.repository.WorkerProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WorkerProfileRepository workerProfileRepository;
    private final EmployerCompanyProfileRepository employerProfileRepository;

    public UserResponse getUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return buildUserResponse(user);
    }

    public List<UserResponse> getUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::buildUserResponse)
                .toList();
    }

    private UserResponse buildUserResponse(User user) {

        // User is Worker
        if (user.getRole() == UserRole.WORKER) {

            WorkerProfile profile = workerProfileRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new IllegalStateException("Profile not found"));

            return UserResponse.from(user, WorkerProfileResponse.from(profile));

        }

        // User is Employer
        if (user.getRole() == UserRole.EMPLOYER) {

            List<EmployerCompanyProfileResponse> profiles = employerProfileRepository.findAllByUserId(user.getId())
                    .stream()
                    .map(EmployerCompanyProfileResponse::from)
                    .toList();

            return UserResponse.from(user, EmployerProfileResponse.from(profiles));
        }

        return UserResponse.from(user, null);

    }
}
