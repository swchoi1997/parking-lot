package com.example.parking.global.security.jwt;

import com.example.parking.global.redis.RedisService;
import com.example.parking.global.security.member.AdminDetailsService;
import com.example.parking.global.security.member.MemberDetailsService;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDetailsService memberDetailsService;

    private final AdminDetailsService adminDetailsService;

    private final RedisTemplate redisTemplate;
    private final RedisService redisService;

    String HEADER_STRING = "X-AUTH-TOKEN";
    String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_STRING); // header에서 X-AUTH-TOKEN 가져옴

        String accessToken =
                (header != null && header.startsWith(TOKEN_PREFIX)) ? header.replace(TOKEN_PREFIX, "") : null;

        // Request Header 에 Access Token (Authorization) 이 담긴 경우
        if (!ObjectUtils.isEmpty(accessToken)) {
            // Access Token 이 만료된 경우
            if (jwtTokenProvider.isTokenExpired(accessToken)) {
                throw new JwtException("access token is expired");
            }
            // Access Token 이 redis에 없는경우(블랙리스트)
            String redisAccessToken = redisService.getData("logout" + accessToken);
            if (redisAccessToken == null) {
                // Access Token 이 유효한 경우
                if (jwtTokenProvider.validateToken(accessToken)
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication = getUserOrAdminNameAndGetAuth(request, accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUserOrAdminNameAndGetAuth(HttpServletRequest request,
                                                                             String accessToken) {
        if (jwtTokenProvider.getEmailFromToken(accessToken) != null) {
            String email = jwtTokenProvider.getEmailFromToken(accessToken);
            UserDetails userDetails = memberDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            return authentication;

        }
        String adminName = jwtTokenProvider.getAdminNameFromToken(accessToken);
        UserDetails userDetails = adminDetailsService.loadUserByUsername(adminName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}
