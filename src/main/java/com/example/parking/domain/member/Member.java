package com.example.parking.domain.member;

import com.example.parking.domain.building.Building;
import com.example.parking.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer building_number;

    @Column(nullable = false)
    private Integer room_number;

    @Column(nullable = false)
    private Integer car_number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Building building;

    //==연관관계 편의 메서드==//
    public void setBuilding(Building building) {
        if (this.building != null) {
            this.building.getMemberList().remove(this);
        }
        this.building = building;
        building.getMemberList().add(this);
    }
}
