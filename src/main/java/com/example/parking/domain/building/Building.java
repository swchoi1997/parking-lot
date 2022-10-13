package com.example.parking.domain.building;

import com.example.parking.domain.admin.Admin;
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

    @Builder.Default
    @OneToMany(mappedBy = "admin")
    private List<Admin> adminList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Member> memberList = new ArrayList<>();
}
