package com.anytime.studymaker.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailService {

    private JavaMailSender mailSender;
    private static final String AUTH_SUBJECT = "스터디메이커 비밀번호 찾기 인증 메일";
    private static final String AUTH_LINK = "http://studymaker.space/initialization/";

    public void sendAuthenticationMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String link = AUTH_LINK+email;
        message.setTo(email);
        message.setSubject(AUTH_SUBJECT);
        message.setText(link);

        mailSender.send(message);
    }

}
