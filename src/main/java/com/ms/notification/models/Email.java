package com.ms.notification.models;

import java.time.LocalDateTime;

import com.ms.notification.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade responsável por representar um registro de envio de e-mail no sistema
 * de notificações. Cada instância desta classe armazena os dados de origem,
 * destino, conteúdo, data de envio e status final do envio.
 *
 * A classe é mapeada para a tabela "email" e utilizada tanto para auditoria
 * quanto para controle de falhas e reprocessamentos.
 */
@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emailId;
    /**
     * ID do usuário relacionado ao envio do e-mail.
     * Pode ser utilizado para rastreamento e auditoria.
     */
    private long userId;

    /** Endereço de e-mail do remetente. */
    private String emailFrom;

    /** Endereço de e-mail do destinatário. */
    private String emailTo;

    /** Assunto do e-mail. */
    private String subject;

    /**
     * Corpo do e-mail. O uso de TEXT permite armazenar conteúdos grandes.
     */
    @Column(columnDefinition = "TEXT")
    private String text;

    /** Data e hora em que a tentativa de envio foi realizada. */
    private LocalDateTime sendDateEmail;

    /**
     * Status da tentativa de envio do e-mail.
     * Utiliza o enum StatusEmail para indicar se o envio foi realizado com sucesso
     * ou se ocorreu erro.
     */
    @Enumerated(EnumType.STRING)
    private StatusEmail statusEmail;

    // Getters e Setters
    public long getEmailId() {
        return emailId;
    }
    public void setEmailId(long emailId) {
        this.emailId = emailId;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getEmailFrom() {
        return emailFrom;
    }
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
    public String getEmailTo() {
        return emailTo;
    }
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }
    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }
    public StatusEmail getStatusEmail() {
        return statusEmail;
    }
    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }

    
}
