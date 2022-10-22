package com.example.parking.domain.building.entity;


import com.example.parking.domain.admin.entity.Admin;
import com.example.parking.domain.building.dto.UpdateBuildingInfoRequestDto;
import com.example.parking.domain.common.Address;
import com.example.parking.domain.member.entity.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;

    @Column(nullable = false, name = "buildingName")
    private String name;

    @Builder.Default
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "buildingCity")),
            @AttributeOverride(name = "district", column = @Column(name = "buildingDistrict")),
            @AttributeOverride(name = "detail", column = @Column(name = "buildingAddressDetail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "buildingZipCode"))
    })
    private Address buildingAddress = new Address();

    public void updateInfo(UpdateBuildingInfoRequestDto dto) {
        this.name = dto.getName();
        this.buildingAddress = dto.getAddress().toEntity();
    }

    @Builder.Default
    @OneToMany(mappedBy = "building", orphanRemoval = true, cascade = ALL)
    private List<Admin> adminList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "building", orphanRemoval = true, cascade = ALL)
    private List<Member> memberList = new ArrayList<>();
}
