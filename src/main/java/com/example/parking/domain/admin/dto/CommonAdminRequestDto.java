package com.example.parking.domain.admin.dto;

import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.common.Role;
import com.example.parking.global.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class CommonAdminRequestDto {

     @Getter @Builder
     @NoArgsConstructor @AllArgsConstructor
     public static class AdminJoinDto{

          @NotBlank(message = "이름은 필수항목입니다.", groups = ValidationGroups.NotEmptyGroup.class)
          @Pattern(regexp = "[가-힣]{2,4}", message = "올바른 이름을 입력해주세요"
                  , groups = ValidationGroups.PatternCheckGroup.class)
          private String name;

          @NotBlank(message = "번호는 필수항목입니다.", groups = ValidationGroups.NotEmptyGroup.class)
          @Pattern(regexp = "^0([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})$", message = "올바른 번호을 입력해주세요"
                  , groups = ValidationGroups.PatternCheckGroup.class)
          private String phone;

          @NotBlank(message = "관리자 아이디는 필수항목입니다.", groups = ValidationGroups.NotEmptyGroup.class)
          @Pattern(regexp = "[a-zA-Z0-9]{2,15}", message = "아이디는 영문, 숫자 조합만 가능하며 2 ~ 15 자리 까지 가능합니다."
                  , groups = ValidationGroups.PatternCheckGroup.class)
          private String adminName;

          @NotBlank(message = "비밀번호는 필수 입력사항입니다..", groups = ValidationGroups.NotEmptyGroup.class)
          @Pattern(regexp = "^(?=.*\\d)(?=.*[0-9a-zA-Z])(?=.*[~!@#$%^&*()=+])[0-9a-zA-Z\\d~!@#$%^&*()=+]{8,20}",
                  message = "비밀번호는 영문과 숫자 특수문자 조합으로 8 ~ 20자리로 설정해주세요."
                  , groups = ValidationGroups.PatternCheckGroup.class)
          private String password;

          @NotBlank(message = "비밀번호 확인은 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
          private String passwordConfirm;

          @NotBlank(message = "관리 중인 아파트를 선택해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
          private Long buildingId;

          public Admin toEntity(String encodePasswd) {
               return Admin.builder()
                       .name(name)
                       .phone(phone)
                       .adminName(adminName)
                       .password(encodePasswd)
                       .role(Role.ADMIN)
                       .build();
          }


     }

     @Getter @Builder
     @NoArgsConstructor @AllArgsConstructor
     public static class MemberApprovalDto{

          private Long memberId;

     }
}
