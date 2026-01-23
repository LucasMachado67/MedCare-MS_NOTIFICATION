package com.ms.notification.dtos;

import java.util.UUID;
/**
 * DTO (Data Transfer Object) utilizado para transportar os dados necessários
 * para o envio de e-mails através do sistema de mensageria.
 *
 * <p>Este registro representa a estrutura da mensagem recebida pelo
 * consumidor do RabbitMQ antes de ser convertida no modelo {@link com.ms.notification.models.Email}.
 *
 * <p>Ele contém apenas os campos essenciais para envio e registro do e-mail.
 *
 * @param userId  Identificador único do usuário associado ao envio do e-mail.
 * @param emailTo Endereço de e-mail do destinatário.
 * @param subject Assunto do e-mail.
 * @param text    Conteúdo do e-mail (corpo da mensagem).
 */
public class EmailRecordDto{
    /**
     * Identificador único do usuário associado ao envio do e-mail.
     */
    private UUID userId;
    /**
     * Endereço de e-mail do destinatário.
     */
    private String emailTo;
    /**
     * Assunto da mensagem de e-mail.
     */
    private String subject;
    /**
     * Conteúdo textual do e-mail a ser enviado.
     */
    private String text;

    // Getters e setters
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
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
}