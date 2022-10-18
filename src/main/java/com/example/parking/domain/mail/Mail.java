package com.example.parking.domain.mail;

import com.example.parking.domain.common.BaseEntity;
import com.example.parking.domain.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID mailId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String emailCheckToken;

    @Column(nullable = false)
    private Boolean EmailVerified;

    @Column(nullable = false)
    private LocalDateTime emailCheckTokenGeneratedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
