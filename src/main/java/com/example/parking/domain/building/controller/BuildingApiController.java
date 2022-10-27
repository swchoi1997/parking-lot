package com.example.parking.domain.building.controller;


import com.example.parking.domain.building.dto.BuildingResponseDto;
import com.example.parking.domain.building.service.BuildingService;
import com.example.parking.domain.building.dto.RegisterBuildingInfoRequestDto;
import com.example.parking.domain.building.dto.UpdateBuildingInfoRequestDto;
import com.example.parking.global.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/building/")
@RestController
@RequiredArgsConstructor
public class BuildingApiController {

    private final BuildingService buildingService;

    @PostMapping()
    public ResponseEntity<ResultResponse<BuildingResponseDto>> registerBuildingInfo(
            @RequestBody final RegisterBuildingInfoRequestDto dto) {
        ResultResponse<BuildingResponseDto> result = ResultResponse.<BuildingResponseDto>builder()
                .response(buildingService.registerBuilding(dto))
                .status(HttpStatus.CREATED)
                .build();

        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<ResultResponse<BuildingResponseDto>> updateBuildingInfo(
            @RequestBody final UpdateBuildingInfoRequestDto dto) {

        ResultResponse<BuildingResponseDto> result = ResultResponse.<BuildingResponseDto>builder()
                .response(buildingService.updateBuilding(dto))
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<ResultResponse<?>> deleteBuildingInfo(@RequestHeader("building_id") final String building_id) {
        buildingService.deleteBuilding(building_id);
        return null;
    }



}
