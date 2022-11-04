package com.example.parking.global.security.member;


import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.admin.entity.AdminRepository;
import com.example.parking.global.advice.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
//데이터베이스에서 유저정보를 가져옴
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminName) {
        Admin admin = adminRepository.findByAdminName(adminName);
        if (admin == null) {
            throw new MemberNotFoundException();
        }
        return new AdminDetails(admin);
    }


}
