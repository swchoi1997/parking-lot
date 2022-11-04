package com.example.parking.domain.building.service;

import com.example.parking.domain.building.dto.BuildingResponseDto;
import com.example.parking.domain.building.dto.RegisterBuildingInfoRequestDto;
import com.example.parking.domain.building.dto.UpdateBuildingInfoRequestDto;

public interface BuildingService {

    BuildingResponseDto registerBuilding(final RegisterBuildingInfoRequestDto dto);

    BuildingResponseDto updateBuilding(final UpdateBuildingInfoRequestDto dto);

    BuildingResponseDto deleteBuilding(final String building_id);
}
