package com.example.parking.domain.admin.dto;

import com.example.parking.domain.admin.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CommonAdminResponseDto {


    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class AdminJoinResponseDto{
        private Long adminId;
        private String adminName;
        private Long buildingId;

        public AdminJoinResponseDto toDto(final Admin admin) {
            return AdminJoinResponseDto.builder()
                    .adminId(admin.getAdminId())
                    .adminName(admin.getAdminName())
                    .buildingId(admin.getBuilding().getBuildingId())
                    .build();
        }
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberApprovalResponseDto{

        private String username;

        private String name;
    }
}
