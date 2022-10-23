package com.example.parking.domain.mail.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {

    Mail findByEmail(String email);

    Boolean existsByEmail(String email);
}
