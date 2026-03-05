package com.eightyeightdays.jobs_au_backend.user.repository;

import com.eightyeightdays.jobs_au_backend.user.model.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, Long> {

    Optional<WorkerProfile> findByUserId(Long userId);
}
