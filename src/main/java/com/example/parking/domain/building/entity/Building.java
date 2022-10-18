package com.example.parking.domain.building.entity;


import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.building.dto.UpdateBuildingInfoRequestDto;
import com.example.parking.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID buildingId;

    @Column(nullable = false, name = "building_name")
    private String name;

    @Column(nullable = false)
    private String address;

    public void updateInfo(UpdateBuildingInfoRequestDto dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
    }

    @Builder.Default
    @OneToMany(mappedBy = "building", orphanRemoval = true, cascade = ALL)
    private List<Admin> adminList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "building", orphanRemoval = true, cascade = ALL)
    private List<Member> memberList = new ArrayList<>();
}
