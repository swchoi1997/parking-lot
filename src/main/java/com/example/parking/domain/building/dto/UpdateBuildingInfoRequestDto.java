package com.example.parking.domain.building.dto;

import com.example.parking.global.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBuildingInfoRequestDto {

    private String preName;

    private Address preAddress;

    @NotBlank(message = "건물 이름을 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    private String name;

    private Address address;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Address{
        private String city; // 도시
        private String district; // 구
        private String detail; // 상세 주소
        private String zipCode; // 우편번호

        public com.example.parking.domain.common.Address toEntity(){
            return com.example.parking.domain.common.Address.builder()
                    .city(city)
                    .district(district)
                    .detail(detail)
                    .zipCode(zipCode).build();
        }
    }
}

