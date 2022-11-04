package com.example.parking.global.auth.controller;

import com.example.parking.global.auth.dto.LoginRequestDto;
import com.example.parking.global.auth.dto.LoginSuccessResponseDto;
import com.example.parking.global.auth.dto.TokenRefreshRequestDto;
import com.example.parking.global.auth.dto.TokenRefreshResponseDto;
import com.example.parking.global.auth.service.AuthService;
import com.example.parking.global.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("api/login/member")
    public ResponseEntity<ResultResponse<?>> memberLogin(@RequestBody LoginRequestDto dto) {
        ResultResponse<LoginSuccessResponseDto> result = new ResultResponse<>(
                authService.memberLogin(dto), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/login/admin")
    public ResponseEntity<ResultResponse<?>> adminLogin(@RequestBody LoginRequestDto dto) {
        ResultResponse<LoginSuccessResponseDto> result = new ResultResponse<>(
                authService.adminLogin(dto), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/refresh-token")
    public ResponseEntity<ResultResponse<?>> refreshToken(@RequestBody TokenRefreshRequestDto dto) {
        ResultResponse<TokenRefreshResponseDto> result = new ResultResponse<>(
                authService.reissueToken(dto), HttpStatus.OK
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("api/logout")
    public String logout(@RequestHeader("username") String username, @RequestHeader("x-access-token") String accessToken) {
        authService.logout(username, accessToken);
        return "Logout Success";
    }
}
