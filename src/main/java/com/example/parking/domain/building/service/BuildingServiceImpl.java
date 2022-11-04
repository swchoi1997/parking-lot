package com.example.parking.domain.building.service;

import com.example.parking.domain.building.dto.BuildingResponseDto;
import com.example.parking.domain.building.dto.RegisterBuildingInfoRequestDto;
import com.example.parking.domain.building.dto.UpdateBuildingInfoRequestDto;
import com.example.parking.domain.building.entity.Building;
import com.example.parking.domain.building.entity.BuildingRepository;
import com.example.parking.global.advice.exception.BuildingAlreadyExistsException;
import com.example.parking.global.advice.exception.BuildingNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService{

    private final BuildingRepository buildingRepository;


    @Override
    @Transactional
    public BuildingResponseDto registerBuilding(final RegisterBuildingInfoRequestDto dto) {
        checkBuildingExist(dto);
        // 여기 확인
        Building building = Building.builder().name(dto.getName()).buildingAddress(dto.getAddress().toEntity()).build();
        Building registerBuilding = buildingRepository.save(building);

        return new BuildingResponseDto().toResponseDto(registerBuilding);
    }


    private void checkBuildingExist(final RegisterBuildingInfoRequestDto dto) {
        if (buildingRepository.existsByName(dto.getName()) &&
            buildingRepository.existsByBuildingAddressZipCode(dto.getAddress().getZipCode())){
            throw new BuildingAlreadyExistsException();
        }
    }

    @Override
    @Transactional
    public BuildingResponseDto updateBuilding(final UpdateBuildingInfoRequestDto dto) {
        checkBuildingExistPreUpdate(dto);
        Building building = buildingRepository.findByNameAndAddress(dto.getPreName(), dto.getPreAddress().getZipCode());
        building.updateInfo(dto);

        Building updateBuilding = buildingRepository.save(building);

        return new BuildingResponseDto().toResponseDto(updateBuilding);
    }

    private void checkBuildingExistPreUpdate(final UpdateBuildingInfoRequestDto dto) {
        if (!buildingRepository.existsByName(dto.getPreName())) {
            throw new BuildingNotFoundException();
        }
    }

    @Override
    @Transactional
    public BuildingResponseDto deleteBuilding(final String building_id) {
        return null;
    }
}

