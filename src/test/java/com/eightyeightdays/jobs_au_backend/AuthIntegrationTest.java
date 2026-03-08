package com.eightyeightdays.jobs_au_backend;

import com.eightyeightdays.jobs_au_backend.auth.dto.LoginRequest;
import com.eightyeightdays.jobs_au_backend.auth.dto.SignupRequest;
import com.eightyeightdays.jobs_au_backend.user.model.User;
import com.eightyeightdays.jobs_au_backend.user.model.UserRole;
import com.eightyeightdays.jobs_au_backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    void signup_success() throws Exception {

        var req = new SignupRequest(
                "user@test.com",
                "password123",
                "nick",
                "name",
                null,
                null,
                UserRole.WORKER
        );

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());

        assertThat(userRepository.existsByEmail("user@test.com")).isTrue();
    }

    @Test
    void login_success_returns_token() throws Exception {

        // given
        SignupRequest signup = new SignupRequest(
                "user@test.com",
                "password123",
                "nick",
                "name",
                null,
                null,
                UserRole.WORKER
        );

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signup)));

        LoginRequest login = new LoginRequest(
                "user@test.com",
                "password123"
        );

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @ParameterizedTest
    @EnumSource(value = UserRole.class, names = {"WORKER", "EMPLOYER"})
    void worker_or_employer_can_access_user_endpoint(UserRole role) throws Exception {

        String token = obtainAccessToken("user@test.com", "abc123!", role);

        mockMvc.perform(get("/api/test/worker")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @EnumSource(value = UserRole.class, names = {"WORKER", "EMPLOYER"})
    void user_cannot_access_admin_endpoint(UserRole role) throws Exception {

        String token = obtainAccessToken("user2@test.com", "abc123!", role);

        mockMvc.perform(get("/api/test/admin")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @EnumSource(value = UserRole.class, names = {"ADMIN"})
    void admin_can_access_admin_endpoint(UserRole role) throws Exception {

        String token = obtainAccessToken("admin@test.com", "abc123!", role);

        mockMvc.perform(get("/api/test/admin")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private String obtainAccessToken(String email, String password, UserRole role) throws Exception {

        // 1.signup
        SignupRequest signup = new SignupRequest(
                email,
                password,
                "nick",
                "name",
                null,
                null,
                role
        );

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signup)))
                .andExpect(status().isCreated());

        // 2. login -> get access token
        return loginAndGetAccessToken(email, password);
    }

    private String loginAndGetAccessToken(String email, String password) throws Exception {

        LoginRequest login = new LoginRequest(email, password);

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();

        return objectMapper.readTree(body)
                .get("accessToken")
                .asText();
    }
}
