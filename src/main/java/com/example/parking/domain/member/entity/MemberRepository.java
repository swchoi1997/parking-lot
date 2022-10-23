package com.example.parking.domain.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(final String email);

    Boolean existsByCarNumber(final String carNumber);
}
