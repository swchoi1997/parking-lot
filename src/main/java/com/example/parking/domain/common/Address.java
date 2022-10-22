package com.example.parking.domain.common;

import com.example.parking.domain.building.dto.RegisterBuildingInfoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Column(nullable = false)
    private String city; // 도시

    @Column(nullable = false)
    private String district; // 구

    @Column(name = "address_detail", nullable = false)
    private String detail; // 상세 주소

    @Column(nullable = false)
    private String zipCode; // 우편번호

}
