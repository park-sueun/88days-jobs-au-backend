package com.eightyeightdays.jobs_au_backend.user.controller;

import com.eightyeightdays.jobs_au_backend.auth.model.CustomUserDetails;
import com.eightyeightdays.jobs_au_backend.user.dto.UserResponse;
import com.eightyeightdays.jobs_au_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id
    ) {
        return userService.getUser(id);
    }

    @GetMapping("/me")
    public UserResponse getMe(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return userService.getUser(user.getUserId());
    }

}
