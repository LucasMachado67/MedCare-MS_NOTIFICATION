package com.ms.notification.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.notification.enums.StatusEmail;
import com.ms.notification.models.Email;
import com.ms.notification.repositories.EmailRepository;

@Service
public class EmailService {


    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email){
        try {

            email.setSendDateEmail(LocalDateTime.now());   
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            return repository.save(email);

        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println("ERROR OCURRED: " + e.getMessage());
            return repository.save(email);
        }
    }
}
