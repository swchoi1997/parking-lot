package com.example.parking.domain.member.dto;

import com.example.parking.domain.common.Address;
import com.example.parking.global.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CommonMemberRequestDto {

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberJoinRequestDto{

        @NotBlank(message = "이름은 필수항목입니다.", groups = ValidationGroups.NotEmptyGroup.class)
        @Pattern(regexp = "[가-힣]{2,4}", message = "올바른 이름을 입력해주세요"
                , groups = ValidationGroups.PatternCheckGroup.class)
        private String name;

        @NotBlank(message = "번호는 필수항목입니다.", groups = ValidationGroups.NotEmptyGroup.class)
        @Pattern(regexp = "^0([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})$", message = "올바른 번호을 입력해주세요"
                , groups = ValidationGroups.PatternCheckGroup.class)
        private String phone;

        @NotBlank(message = "이메일을 입력해 주세요.", groups = ValidationGroups.NotEmptyGroup.class)
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력사항입니다..", groups = ValidationGroups.NotEmptyGroup.class)
        @Pattern(regexp = "^(?=.*\\d)(?=.*[0-9a-zA-Z])(?=.*[~!@#$%^&*()=+])[0-9a-zA-Z\\d~!@#$%^&*()=+]{8,20}",
                message = "비밀번호는 영문과 숫자 특수문자 조합으로 8 ~ 20자리로 설정해주세요."
                , groups = ValidationGroups.PatternCheckGroup.class)
        private String password;

        @NotBlank(message = "비밀번호 확인은 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
        private String passwordConfirm;

        @Builder.Default
        @NotBlank(message = "입주 한 아파트를 선택해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
        private Building_Info building_info = new Building_Info();

        @Builder.Default
        @NotBlank(message = "상세주소를 입력해 주세요.", groups = ValidationGroups.NotEmptyGroup.class)
        private Home_Info homeInfo = new Home_Info();

        @NotBlank(message = "자동차 번호를 입력하세요", groups = ValidationGroups.NotEmptyGroup.class)
        private String carNumber;

    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Home_Info{

        private String building_number;

        private String room_number;
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Building_Info{

        private String building_Name;

        private Address building_Addr;
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Building_Addr{

        /**
         * 도시
         */
        private String city;

        /**
         * 구
         */
        private String district;
        /**
         * 상세주소
         */
        private String detail;
        /**
         * 우편주소
         */
        private String zipCode;
    }



    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class EmailTokenRequestDto{

        private String email;

        private String emailValidToken;
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class FindEmailRequestDto{
        private String name;
        private String carNumber;
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class FindPasswordRequestDto{
        private String name;

        private String email;

        private String carNumber;

    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class DeleteMemberRequestDto{

        private String email;

        private String carNumber;

    }


}
