package com.example.parking.domain.mail.entity;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class EmailAuthCustomRepositoryImpl implements EmailAuthCustomRepository {

    JPAQueryFactory jpaQueryFactory;

    public EmailAuthCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Mail> findValidAuthByEmail(String email, String emailCheckToken, LocalDateTime currentTime) {
        Mail emailAuth = jpaQueryFactory
                .selectFrom(QMail.mail)
                .where(QMail.mail.email.eq(email),
                        QMail.mail.emailCheckToken.eq(emailCheckToken),
                        QMail.mail.expireTime.goe(currentTime),
                        QMail.mail.expireToken.eq(false))
                .fetchFirst();
        return Optional.ofNullable(emailAuth);
    }
}
