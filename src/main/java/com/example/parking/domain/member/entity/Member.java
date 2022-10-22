package com.example.parking.domain.member.entity;

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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Embedded
    private HomeInfo homeInfo = new HomeInfo();

    @Column(nullable = false)
    private String carNumber;

    @Builder.Default
    @Column(nullable = false)
    private Boolean approvalStatus = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "building_id")
    private Building building;



    //==연관관계 편의 메서드==//
    public void setBuilding(final Building building) {
        if (this.building != null) {
            this.building.getMemberList().remove(this);
        }
        this.building = building;
        building.getMemberList().add(this);
    }

    public void setApprovalStatus() {
        this.approvalStatus = true;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
