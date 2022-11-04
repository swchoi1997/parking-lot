package com.example.parking.global.auth.service;

import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.admin.entity.AdminRepository;
import com.example.parking.domain.member.entity.Member;
import com.example.parking.domain.member.entity.MemberRepository;
import com.example.parking.global.advice.exception.InvalidRefreshTokenException;
import com.example.parking.global.advice.exception.MemberNotFoundException;
import com.example.parking.global.auth.dto.LoginRequestDto;
import com.example.parking.global.auth.dto.LoginSuccessResponseDto;
import com.example.parking.global.auth.dto.TokenRefreshRequestDto;
import com.example.parking.global.auth.dto.TokenRefreshResponseDto;
import com.example.parking.global.redis.RedisKey;
import com.example.parking.global.redis.RedisService;
import com.example.parking.global.security.jwt.JwtTokenProvider;
import com.example.parking.global.security.member.AdminDetails;
import com.example.parking.global.security.member.AdminDetailsService;
import com.example.parking.global.security.member.MemberDetails;
import com.example.parking.global.security.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final AuthenticationManager auth;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;
    private final AdminDetailsService adminDetailsService;


    @Override
    public LoginSuccessResponseDto memberLogin(LoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getUsername()).orElseThrow(MemberNotFoundException::new);
        auth.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        String memberAccessToken = jwtTokenProvider.createMemberAccessToken(dto.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(dto.getUsername());

        redisService.setDataWithExpiration(RedisKey.REFRESH.getKey() + member.getEmail(), refreshToken,
                JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(member.getEmail());

        return new LoginSuccessResponseDto(memberDetails.getId(), memberDetails.getUsername(), memberAccessToken,
                refreshToken);
    }

    @Override
    public LoginSuccessResponseDto adminLogin(LoginRequestDto dto) {
        Admin admin = adminRepository.findByAdminName(dto.getUsername());
        if (admin == null) {
            throw new MemberNotFoundException();
        }
        auth.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        String adminNameAccessToken = jwtTokenProvider.createAdminAccessToken(dto.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(dto.getUsername());

        redisService.setDataWithExpiration(RedisKey.REFRESH.getKey() + admin.getAdminName(), refreshToken,
                JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        AdminDetails adminDetails = (AdminDetails) adminDetailsService.loadUserByUsername(admin.getAdminName());

        return new LoginSuccessResponseDto(adminDetails.getId(), adminDetails.getUsername(), adminNameAccessToken,
                refreshToken);
    }

    @Override
    public TokenRefreshResponseDto reissueToken(TokenRefreshRequestDto dto) {
        checkRefreshTokenExpired(dto.getRefreshToken());

        String usernameFromToken = jwtTokenProvider.getUsernameFromToken(dto.getAccessToken());
        String redisServiceFromRedis = redisService.getData(RedisKey.REFRESH.getKey() + usernameFromToken);

        validateRefreshTokenFromRedis(dto.getRefreshToken(), redisServiceFromRedis);
        if (!usernameFromToken.contains("@")) {
            return new TokenRefreshResponseDto(reCreateAdminAccessToken(usernameFromToken));
        }
        return new TokenRefreshResponseDto(reCreateMemberAccessToken(usernameFromToken));

    }

    private String reCreateMemberAccessToken(String usernameFromToken) {
        Member member = memberRepository.findByEmail(usernameFromToken).orElseThrow(MemberNotFoundException::new);
        return jwtTokenProvider.createMemberAccessToken(member.getEmail());
    }

    private String reCreateAdminAccessToken(String usernameFromToken) {
        Admin admin = adminRepository.findByAdminName(usernameFromToken);
        if (admin == null) throw new MemberNotFoundException();

        return jwtTokenProvider.createAdminAccessToken(admin.getAdminName());
    }

    private static void validateRefreshTokenFromRedis(String refreshToken, String redisServiceFromRedis) {
        if (redisServiceFromRedis == null || !redisServiceFromRedis.equals(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }
    }

    private void checkRefreshTokenExpired(String refreshToken) {
        if (jwtTokenProvider.isTokenExpired(refreshToken) || !jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }
    }

    @Override
    public void logout(String username, String accessToken) {
        redisService.deleteData(RedisKey.REFRESH.getKey() + username);
        redisService.setDataWithExpiration("logout" + accessToken.substring(7), accessToken.substring(7),
                jwtTokenProvider.getExpirationDateFromToken(accessToken.substring(7)).getTime() / 1000000);
    }
}

