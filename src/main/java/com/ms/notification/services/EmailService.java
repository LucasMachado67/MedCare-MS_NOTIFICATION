package com.ms.notification.services;

import java.time.LocalDateTime;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ms.notification.enums.StatusEmail;
import com.ms.notification.models.Email;
import com.ms.notification.repositories.EmailRepository;

/**
 * Serviço responsável pelo envio de e-mails e pelo gerenciamento
 * do registro de envio no banco de dados.
 *
 * <p>Esta classe realiza a montagem da mensagem, utiliza o
 * {@link JavaMailSender} para enviar o e-mail e persiste o
 * resultado da operação no repositório {@link EmailRepository}.
 *
 * <p>Em caso de falha no envio, o status do e-mail é atualizado
 * para {@link StatusEmail#ERROR}.
 */
@Service
public class EmailService {

    private final String emailFrom;
    private JavaMailSenderImpl sender;

    @Autowired
    private EmailRepository repository;

    /**
     * Envia um e-mail com base nos dados fornecidos pelo objeto {@link Email}
     * e registra o resultado da operação no banco de dados.
     *
     * <p>O método define a data de envio, seta o remetente com base na
     * configuração da aplicação e tenta realizar o envio através do
     * {@link JavaMailSender}. Em seguida, o status é atualizado para
     * {@link StatusEmail#SENT} em caso de sucesso ou
     * {@link StatusEmail#ERROR} em caso de erro.
     *
     * <p>O método é anotado com {@link Transactional}, garantindo que
     * a operação de persistência seja tratada como uma transação.
     *
     * @param email Objeto contendo os dados do e-mail a ser enviado.
     * @return O objeto {@link Email} atualizado com o status de envio.
     */
    public EmailService() {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(System.getenv("SMTP_HOST"));
        sender.setPort(Integer.parseInt(System.getenv("SMTP_PORT")));
        sender.setUsername(System.getenv("SMTP_USER"));
        sender.setPassword(System.getenv("SMTP_PASS"));

        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        this.sender = sender;
        this.emailFrom = System.getenv("SMTP_USER");
    }

    @Transactional
    public Email sendEmail(Email email){
        try {

            email.setSendDateEmail(LocalDateTime.now());   
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            sender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            return repository.save(email);

        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println("ERROR OCURRED: " + e.getMessage());
            return repository.save(email);
        }
    }
}
