package com.eightyeightdays.jobs_au_backend.controller;

import com.eightyeightdays.jobs_au_backend.auth.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestAuthController {

    // WORKER 접근 가능
    @GetMapping("/worker")
    public String userEndpoint(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return "WORKER OK: " + user.getEmail();
    }

    // WORKER 접근 가능
    @GetMapping("/employer")
    public String employerEndpoint(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return "Employer OK: " + user.getEmail();
    }

    // ADMIN만 접근 가능
    @GetMapping("/admin")
    public String adminEndpoint(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return "ADMIN OK: " + user.getEmail();
    }

    // 인증만 되면 접근 가능
    @GetMapping("/auth")
    public String authEndpoint(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return "AUTH OK: " + user.getUserId();
    }
}
