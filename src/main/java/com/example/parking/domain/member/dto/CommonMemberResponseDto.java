package com.example.parking.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommonMemberResponseDto {

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class MemberJoinResponseDto{

        private Long memberId;
        private String email;
        private String carNumber;

    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class FindEmailResponseDto{
        String email;
    }

    @Getter @Builder
    @NoArgsConstructor

    public static class FindPasswordResponseDto{

    }
}
