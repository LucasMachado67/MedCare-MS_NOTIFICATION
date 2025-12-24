package com.ms.notification.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.notification.dtos.EmailRecordDto;
import com.ms.notification.models.Email;
import com.ms.notification.services.EmailService;
/**
 * Consumer responsável por receber mensagens de e-mail provenientes do broker
 * RabbitMQ. Quando um evento chega na fila configurada, os dados são
 * convertidos em um modelo {@link Email} e encaminhados para o serviço de envio.
 *
 * Esta classe atua como ponto de integração entre a mensageria e o serviço de
 * notificação, garantindo que cada mensagem recebida represente uma tentativa
 * válida de envio de e-mail.
 */
@Component
public class EmailConsumer {

    public EmailConsumer() {
        super();
        //TODO Auto-generated constructor stub
    }

    @Autowired
    private EmailService service;
    /**
     * Escuta a fila definida em <code>broker.queue.email.name</code> no arquivo
     * de configuração. Sempre que uma nova mensagem do tipo {@link EmailRecordDto}
     * é publicada na fila, este método é acionado automaticamente.
     *
     * <p>
     * Utiliza {@link BeanUtils#copyProperties(Object, Object)} para copiar os
     * dados do DTO para o modelo {@link Email}, preservando o padrão de dados
     * esperado pelo serviço de envio.
     * </p>
     *
     * @param dto Objeto recebido da fila contendo os dados necessários para
     *            a construção e envio do e-mail.
     */
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto dto){
        var emailModel = new Email();
        BeanUtils.copyProperties(dto, emailModel);
        service.sendEmail(emailModel);
    }
}
