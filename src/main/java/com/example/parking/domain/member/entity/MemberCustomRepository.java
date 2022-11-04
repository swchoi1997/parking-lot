package com.example.parking.domain.member.entity;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<Member> findMemberByNameAndCarNumber(String name, String carNumber);

    Optional<Member> findMemberByEmailAndCarNumber(String email, String carNumber);
}
