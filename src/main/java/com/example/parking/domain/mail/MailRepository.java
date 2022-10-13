package com.example.parking.domain.mail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailRepository extends JpaRepository<Mail, UUID> {
}
