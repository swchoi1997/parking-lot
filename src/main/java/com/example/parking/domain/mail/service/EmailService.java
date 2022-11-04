package com.example.parking.domain.mail.service;

import com.example.parking.domain.mail.dto.EmailMessage;
import com.example.parking.domain.mail.entity.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {

    private final static Long MAX_EXPIRE_TIME = 5L;
    private final JavaMailSender javaMailSender;
    public void sendEmail(final EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getMessage(), true);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public Mail saveMailInfo(final String email) {
        String emailToken = UUID.randomUUID().toString();

        return Mail.builder()
                .email(email)
                .emailCheckToken(emailToken.substring(0, 6))
                .limitSendCount(1)
                .expireTime(setTokenExpireTime())
                .expireToken(false)
                .build();

    }
    private LocalDateTime setTokenExpireTime(){
        return LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }
}

