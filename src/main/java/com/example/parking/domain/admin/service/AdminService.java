package com.example.parking.domain.admin.service;


import static com.example.parking.domain.admin.dto.CommonAdminRequestDto.*;
import static com.example.parking.domain.admin.dto.CommonAdminResponseDto.*;

public interface AdminService {
    AdminJoinResponseDto joinAdmin(final AdminJoinDto adminJoinDto);
    MemberApprovalResponseDto memberApprovalByAdmin(final MemberApprovalDto memberApprovalDto);
}
