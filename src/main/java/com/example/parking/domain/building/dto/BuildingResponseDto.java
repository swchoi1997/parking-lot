package com.example.parking.domain.building.dto;

import com.example.parking.domain.building.entity.Building;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class BuildingResponseDto {

    private Long buildingId;

    private String name;

    public BuildingResponseDto toResponseDto(final Building building) {
        return BuildingResponseDto.builder()
                .buildingId(building.getBuildingId())
                .name(building.getName())
                .build();
    }
}
