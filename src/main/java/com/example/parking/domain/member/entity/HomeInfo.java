package com.example.parking.domain.member.entity;

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
public class HomeInfo {

    @Column(nullable = false)
    private String building_number;

    @Column(nullable = false)
    private String room_number;

}
