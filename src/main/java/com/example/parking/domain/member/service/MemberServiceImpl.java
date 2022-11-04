package com.example.parking.domain.member.service;

import com.example.parking.domain.building.entity.Building;
import com.example.parking.domain.building.entity.BuildingRepository;
import com.example.parking.domain.common.Role;
import com.example.parking.domain.mail.dto.EmailMessage;
import com.example.parking.domain.mail.entity.EmailAuthCustomRepository;
import com.example.parking.domain.mail.entity.Mail;
import com.example.parking.domain.mail.entity.MailRepository;
import com.example.parking.domain.mail.service.EmailService;
import com.example.parking.domain.member.entity.HomeInfo;
import com.example.parking.domain.member.entity.Member;
import com.example.parking.domain.member.entity.MemberCustomRepository;
import com.example.parking.domain.member.entity.MemberRepository;
import com.example.parking.global.advice.exception.EmailAuthTokenNotFountException;
import com.example.parking.global.advice.exception.MemberNotFoundException;
import com.example.parking.global.advice.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.parking.domain.member.dto.CommonMemberResponseDto.*;
import static com.example.parking.domain.member.dto.CommonMemberRequestDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    public static final String SUCCESS_SEND_EMAIL = "이메일을 확인 해 주세요. 서버상황에 따라 최대 5분정도 소요될 수 있습니다.";
    private final MemberRepository memberRepository;
    private final MemberCustomRepository memberCustomRepository;
    private final BuildingRepository buildingRepository;
    private final MailRepository mailRepository;
    private final EmailAuthCustomRepository emailAuthCustomRepository;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;


    @Override
    public MemberJoinResponseDto joinMember(final MemberJoinRequestDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail()) || memberRepository.existsByCarNumber(dto.getCarNumber())) {
            throw new UserAlreadyExistsException();
        }
        Member joinMember = toEntity(dto);
        Building building = buildingRepository.findByNameAndAddress(dto.getBuilding_info().getBuilding_Name(),
                dto.getBuilding_info().getBuilding_Addr().getZipCode());
        joinMember.setBuilding(building);

        Member saveMember = memberRepository.save(joinMember);

        return MemberJoinResponseDto.builder().memberId(saveMember.getMemberId())
                .email(saveMember.getEmail()).carNumber(saveMember.getCarNumber()).build();

    }

    private Member toEntity(final MemberJoinRequestDto dto) {
        return Member.builder()
                .name(dto.getName()).phone(dto.getPhone()).email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .homeInfo(new HomeInfo(dto.getHomeInfo().getBuilding_number(), dto.getHomeInfo().getRoom_number()))
                .carNumber(dto.getCarNumber())
                .approvalStatus(false).role(Role.USER)
                .build();
    }

    @Override
    public String checkEmail(final String email) { // 이메일 가입확인 && 이메일 토큰 저장
        Mail mail = mailRepository.findByEmail(email);
        EmailMessage emailMessage = null;
        if (mail == null) {
            Mail savedMail = mailRepository.save(emailService.saveMailInfo(email));
            emailMessage = setEmailMessageHtml(savedMail);
            emailService.sendEmail(emailMessage);
            return SUCCESS_SEND_EMAIL;
        }
        mail.setEmailCheckToken();
        mail.checkLimitEmailSendCount();
        emailMessage = setEmailMessageHtml(mail);
        mail.upLimitSendCount();
        emailService.sendEmail(emailMessage);

        return SUCCESS_SEND_EMAIL;
    }

//    private void checkLimitEmailSendCount(Mail mail) {
//        if (mail.getLimitSendCount() > 5) {
//            throw new CanNotSendEmailValidTokenByOverCount("인증 가능 횟수를 초과하였습니다. 관리자에게 문의하세요");
//        }
//    }

    private EmailMessage setEmailMessageHtml(final Mail mail) {
        Context context = new Context();
        context.setVariable("token", mail.getEmailCheckToken());
        context.setVariable("email", mail.getEmail());
        context.setVariable("message", "이메일 인증을 완료하기 위해서 아래 문자를 입력해 주세요");

        String message = templateEngine.process("mail/check-emailToken", context);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(mail.getEmail()).subject("회원가입 이메일 인증")
                .message(message).build();
        return emailMessage;
    }

    @Override
    public String checkEmailToken(final EmailTokenRequestDto dto) {
        Mail mail = emailAuthCustomRepository.findValidAuthByEmail(
                dto.getEmail(), dto.getEmailValidToken(), LocalDateTime.now())
                .orElseThrow(EmailAuthTokenNotFountException::new);

        mail.setExpireToken();

        return "이메일 인증이 완료되었습니다.";
    }

    @Override
    public FindEmailResponseDto findEmail(final FindEmailRequestDto dto) {
        Member member = memberCustomRepository.findMemberByNameAndCarNumber(dto.getName(), dto.getCarNumber())
                .orElseThrow(MemberNotFoundException::new);
        return new FindEmailResponseDto(member.getEmail());
    }

    @Override
    @Transactional
    public String findPassword(final FindPasswordRequestDto dto) {
        String emailToken = UUID.randomUUID().toString();
        emailToken = emailToken.substring(0, 6);
        Member member = memberCustomRepository.findMemberByNameAndCarNumber(dto.getName(), dto.getCarNumber())
                .orElseThrow(MemberNotFoundException::new);
        member.updatePassword(passwordEncoder.encode(emailToken));

        emailService.sendEmail(setTmpPasswdMessageHtml(member, emailToken));
        memberRepository.save(member);

        return "비밀번호가 수정되었습니다.";
    }

    private EmailMessage setTmpPasswdMessageHtml(final Member member, final String tmpPassword) {
        Context context = new Context();

        context.setVariable("token", tmpPassword);
        context.setVariable("email", member.getEmail());
        context.setVariable("message", "임시 비밀번호를 발급하였씁니다.");

        String message = templateEngine.process("mail/check-emailToken", context);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(member.getEmail()).subject("회원가입 이메일 인증")
                .message(message).build();
        return emailMessage;
    }

    @Override
    public String delMember(final DeleteMemberRequestDto dto) {
        return null;
    }
}
