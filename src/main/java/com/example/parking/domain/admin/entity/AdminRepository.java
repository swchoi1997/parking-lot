package com.example.parking.domain.admin.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminName(final String adminName);

    boolean existsByAdminName(String adminName);
}
