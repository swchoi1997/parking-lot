package com.example.parking.domain.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Boolean existsByEmail(String email);

    Boolean existsByCarNum(String car_number);
}
