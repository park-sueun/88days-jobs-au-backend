package com.eightyeightdays.jobs_au_backend.user.service;

import com.eightyeightdays.jobs_au_backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

}
