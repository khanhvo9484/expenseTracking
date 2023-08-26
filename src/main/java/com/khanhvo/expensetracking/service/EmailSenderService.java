package com.khanhvo.expensetracking.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RequiredArgsConstructor
@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    public void sendEmailWithTemplate(String recipientEmail, String recipientName, String verificationCode) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Email Verification");

        Context context = new Context();
        context.setVariable("recipientName", recipientName);
        context.setVariable("verificationLink", verificationCode);

        String htmlContent = templateEngine.process("email-template", context);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}
