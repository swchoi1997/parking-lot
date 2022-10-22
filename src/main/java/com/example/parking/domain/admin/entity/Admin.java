package com.example.parking.domain.admin.entity;

import com.example.parking.domain.building.entity.Building;
import com.example.parking.domain.common.BaseEntity;
import com.example.parking.domain.common.Role;
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
public class Admin extends BaseEntity {

//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(columnDefinition = "BINARY(16)")
//    private UUID adminId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Building building;

    //==연관관계 편의 메서드==//
    public void setBuilding(Building building) {
        if (this.building != null) {
            this.building.getAdminList().remove(this);
        }
        this.building = building;
        building.getAdminList().add(this);
    }

}
