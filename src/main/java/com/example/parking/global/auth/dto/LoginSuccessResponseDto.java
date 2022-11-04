package com.example.parking.global.auth.dto;

import com.example.parking.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessResponseDto {

    private Long id;

    private String username;

    private String accessToken;

    private String refreshToken;
}
