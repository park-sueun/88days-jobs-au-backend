package com.eightyeightdays.jobs_au_backend.auth.controller;

import com.eightyeightdays.jobs_au_backend.auth.dto.LoginRequest;
import com.eightyeightdays.jobs_au_backend.auth.dto.RefreshRequest;
import com.eightyeightdays.jobs_au_backend.auth.dto.SignupRequest;
import com.eightyeightdays.jobs_au_backend.auth.dto.TokenResponse;
import com.eightyeightdays.jobs_au_backend.auth.model.CustomUserDetails;
import com.eightyeightdays.jobs_au_backend.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest request) {

        authService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest request) {
        return authService.refresh(request.refreshToken());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@AuthenticationPrincipal CustomUserDetails user) {
        authService.logout(user.getUserId());
    }


}
