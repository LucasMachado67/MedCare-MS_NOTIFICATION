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
public record EmailRecordDto(
    UUID userId,
    String emailTo,
    String subject,
    String text
) {

}
