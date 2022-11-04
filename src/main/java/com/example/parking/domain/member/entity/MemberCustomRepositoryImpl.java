package com.example.parking.domain.member.entity;

import com.example.parking.domain.mail.entity.Mail;
import com.example.parking.domain.mail.entity.QMail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public MemberCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Member> findMemberByNameAndCarNumber(String name, String carNumber) {
        Member member = jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.name.eq(name),
                        QMember.member.carNumber.eq(carNumber))
                .fetchFirst();
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findMemberByEmailAndCarNumber(String email, String carNumber) {
        Member member = jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.email.eq(email),
                        QMember.member.carNumber.eq(carNumber))
                .fetchFirst();
        return Optional.ofNullable(member);
    }
}
