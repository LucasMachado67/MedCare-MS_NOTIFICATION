package com.ms.notification.enums;
/**
 * Enumeração que representa os possíveis status do envio de um e-mail.
 *
 * <p>É utilizada para registrar no banco de dados o resultado da tentativa
 * de envio realizada pelo serviço de notificações.
 *
 * <ul>
 *   <li>{@code SENT} – O e-mail foi enviado com sucesso.</li>
 *   <li>{@code ERROR} – Ocorreu algum erro durante o envio do e-mail.</li>
 * </ul>
 */
public enum StatusEmail {
    SENT,
    ERROR
}   
