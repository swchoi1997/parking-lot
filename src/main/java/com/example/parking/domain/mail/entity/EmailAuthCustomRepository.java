package com.example.parking.domain.mail.entity;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthCustomRepository{
    Optional<Mail> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);
}
