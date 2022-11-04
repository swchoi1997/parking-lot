package com.example.parking.global.auth.service;

import com.example.parking.global.auth.dto.LoginRequestDto;
import com.example.parking.global.auth.dto.LoginSuccessResponseDto;
import com.example.parking.global.auth.dto.TokenRefreshRequestDto;
import com.example.parking.global.auth.dto.TokenRefreshResponseDto;

public interface AuthService {

    LoginSuccessResponseDto memberLogin(LoginRequestDto dto);

    LoginSuccessResponseDto adminLogin(LoginRequestDto dto);

    TokenRefreshResponseDto reissueToken(TokenRefreshRequestDto dto);

    void logout(String username, String accessToken);


}
