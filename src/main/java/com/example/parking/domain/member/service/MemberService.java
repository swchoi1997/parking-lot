package com.example.parking.domain.member.service;


import com.example.parking.domain.member.dto.CommonMemberRequestDto;

import static com.example.parking.domain.member.dto.CommonMemberResponseDto.*;
import static com.example.parking.domain.member.dto.CommonMemberRequestDto.*;
public interface MemberService {

    MemberJoinResponseDto joinMember(final MemberJoinRequestDto dto);

    String checkEmail(final String email);

    String checkEmailToken(final EmailTokenRequestDto dto);

    FindEmailResponseDto findEmail(final FindEmailRequestDto dto);

    String findPassword(final FindPasswordRequestDto dto);

    String delMember(final DeleteMemberRequestDto dto);



}
