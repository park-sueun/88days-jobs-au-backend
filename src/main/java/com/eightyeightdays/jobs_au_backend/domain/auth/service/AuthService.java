package com.eightyeightdays.jobs_au_backend.domain.auth.service;

import com.eightyeightdays.jobs_au_backend.domain.auth.dto.LoginRequest;
import com.eightyeightdays.jobs_au_backend.domain.auth.dto.SignupRequest;
import com.eightyeightdays.jobs_au_backend.domain.auth.dto.TokenResponse;
import com.eightyeightdays.jobs_au_backend.domain.auth.entity.RefreshToken;
import com.eightyeightdays.jobs_au_backend.domain.auth.repository.RefreshTokenRepository;
import com.eightyeightdays.jobs_au_backend.infra.security.CustomUserDetails;
import com.eightyeightdays.jobs_au_backend.infra.security.JwtTokenProvider;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.User;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.UserRole;
import com.eightyeightdays.jobs_au_backend.domain.user.entity.WorkerProfile;
import com.eightyeightdays.jobs_au_backend.domain.user.repository.UserRepository;
import com.eightyeightdays.jobs_au_backend.domain.user.repository.WorkerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final WorkerProfileRepository workerProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // signup
    // =========================
    @Transactional
    public void signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phone(request.phone())
                .profileImageUrl(request.profileImageUrl())
                .role(request.role())
                .build();

        userRepository.save(user);

        if (user.getRole() == UserRole.WORKER) {
            WorkerProfile profile = WorkerProfile.of(user);
            workerProfileRepository.save(profile);
        }

        if (user.getRole() == UserRole.EMPLOYER) {

        }

        if (user.getRole() == UserRole.ADMIN) {

        }

    }


    // =========================
    // login
    // =========================
    @Transactional
    public TokenResponse login(LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();

        User user = userRepository.findById(principal.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED
                ));

        String access = jwtTokenProvider.createAccessToken(user);
        String refresh = jwtTokenProvider.createRefreshToken(user);

        saveOrUpdateRefreshToken(user, refresh);

        return new TokenResponse(access, refresh, user.getRole());
    }


    // =========================
    // refresh token
    // =========================
    @Transactional
    public TokenResponse refresh(String refreshToken) {

        if (!jwtTokenProvider.isValid(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        Long userId = jwtTokenProvider.getUserId(refreshToken);

        RefreshToken stored = refreshTokenRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (!stored.getToken().equals(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh mismatch");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        String newAccess = jwtTokenProvider.createAccessToken(user);
        String newRefresh = jwtTokenProvider.createRefreshToken(user);

        stored.rotate(newRefresh, OffsetDateTime.now().plusDays(14));

        return new TokenResponse(newAccess, newRefresh, user.getRole());
    }

    // =========================
    // logout
    // =========================
    @Transactional
    public void logout(Long userId) {
        refreshTokenRepository.deleteByUser_Id(userId);
    }

    private void saveOrUpdateRefreshToken(User user, String refresh) {
        OffsetDateTime exp = OffsetDateTime.now().plusDays(14);

        refreshTokenRepository.findByUser_Id(user.getId())
                .ifPresentOrElse(
                        rt -> rt.rotate(refresh, exp),
                        () -> refreshTokenRepository.save(
                                new RefreshToken(user, refresh, exp)
                        )
                );
    }

}
