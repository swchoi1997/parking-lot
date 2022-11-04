package com.example.parking.domain.admin.service;

import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.admin.entity.AdminRepository;
import com.example.parking.domain.building.entity.BuildingRepository;
import com.example.parking.domain.common.Role;
import com.example.parking.domain.member.entity.Member;
import com.example.parking.domain.member.entity.MemberRepository;
import com.example.parking.global.advice.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

import static com.example.parking.domain.admin.dto.CommonAdminRequestDto.*;
import static com.example.parking.domain.admin.dto.CommonAdminResponseDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final BuildingRepository buildingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminJoinResponseDto joinAdmin(final AdminJoinDto adminJoinDto) {
        beforeProcessJoinAdmin(adminJoinDto);
        Admin admin = adminJoinDto.toEntity(passwordEncoder.encode(adminJoinDto.getPassword()));

        admin.setBuilding(buildingRepository.findById(adminJoinDto.getBuildingId())
                            .orElseThrow(BuildingNotRegisterException::new));

        adminRepository.save(admin);

        Admin byAdminName = adminRepository.findByAdminName(adminJoinDto.getAdminName());
        return new AdminJoinResponseDto().toDto(byAdminName);
    }

    private void beforeProcessJoinAdmin(final AdminJoinDto adminJoinDto) {
        checkAdminName(adminJoinDto.getAdminName());
        checkPasswordValid(adminJoinDto.getPassword(), adminJoinDto.getPasswordConfirm());
    }
    private void checkAdminName(final String adminName) {
        if (adminRepository.findByAdminName(adminName) != null) {
            throw new UserAlreadyExistsException();
        }
    }
    private void checkPasswordValid(final String password, final String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new PasswordNotCorrectException();
        }
    }

    @Override
    @Transactional
    public MemberApprovalResponseDto memberApprovalByAdmin(final Admin admin, final MemberApprovalDto memberApprovalDto) {
        checkAdminExists(admin);
        Member member = memberRepository.findById(memberApprovalDto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        if (member.getApprovalStatus()) throw new MemberAlreadyApprovalException();
        member.setApprovalStatus();
        memberRepository.save(member);

        return MemberApprovalResponseDto.builder().username(member.getEmail()).name(member.getName()).build();
    }

    private void checkAdminExists(Admin admin) {
        if (!adminRepository.existsByAdminName(admin.getAdminName())) {
            throw new MemberNotFoundException();
        }
    }
}
