package com.example.parking.domain.mail.entity;

import com.example.parking.domain.common.BaseEntity;
import com.example.parking.domain.common.Role;
import com.example.parking.global.advice.exception.CanNotSendEmailValidTokenByOverCount;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String emailCheckToken;

    @Column(nullable = false)
    private int limitSendCount;

    @Column(nullable = false)
    private LocalDateTime expireTime;

    private Boolean expireToken;

    public void upLimitSendCount(){
        limitSendCount += 1;
    }

    public void setExpireToken(){
        this.expireToken = true;
        this.limitSendCount = 0;
        this.emailCheckToken = null;
    }

    public void setEmailCheckToken(){
        String emailToken = UUID.randomUUID().toString();
        this.emailCheckToken = emailToken.substring(0, 6);
    }

    public void checkLimitEmailSendCount() {
        if (this.limitSendCount > 5) {
            throw new CanNotSendEmailValidTokenByOverCount("인증 가능 횟수를 초과하였습니다. 관리자에게 문의하세요");
        }
    }
}
