package com.ms.notification.consumers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.notification.dtos.EmailRecordDto;
import com.ms.notification.models.Email;
import com.ms.notification.services.EmailService;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer responsável por receber mensagens de e-mail provenientes do broker
 * RabbitMQ. Quando um evento chega na fila configurada, os dados são
 * convertidos num modelo {@link Email} e encaminhados para o serviço de envio.
 *
 * Esta classe atua como ponto de integração entre a mensageria e o serviço de
 * notificação, garantindo que cada mensagem recebida represente uma tentativa
 * válida de envio de e-mail.
 */
@Component
public class EmailConsumer {

    @Autowired
    private EmailService service;
    

     /**
     * Escuta a fila definida no arquivo
     * de configuração. Sempre que uma nova mensagem do tipo {@link EmailRecordDto}
     * é publicada na fila, este método é acionado automaticamente.
     *
     * <p>
     * Utiliza {@link BeanUtils#copyProperties(Object, Object)} para copiar os
     * dados do DTO para o modelo {@link Email}, preservando o padrão de dados
     * esperado pelo serviço de envio.
     * </p>
     */
    @SqsListener(value = "${medcare.aws.sqs.queue.notification.email}")
    public void listenEmailQueue(String payload){
        System.out.println("Received Message" + payload);
        try {
            Email emailModel = new Email();
            EmailRecordDto event =
                new ObjectMapper().readValue(payload, EmailRecordDto.class);

            BeanUtils.copyProperties(event, emailModel);
            service.sendEmail(emailModel);
        } catch (Exception e) {
            System.err.println("Erro ao processar evento: " + e.getMessage());
        }
    }

}
