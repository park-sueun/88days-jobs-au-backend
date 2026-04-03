package com.eightyeightdays.jobs_au_backend.domain.user.service;

import com.eightyeightdays.jobs_au_backend.domain.user.dto.UserResponse;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.User;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.UserRole;
import com.eightyeightdays.jobs_au_backend.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return UserResponse.from(user);
    }

    public List<UserResponse> getUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponse::from)
                .toList();
    }

}
