package com.ms.notification.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.notification.dtos.EmailRecordDto;
import com.ms.notification.models.Email;
import com.ms.notification.services.EmailService;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService service;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto dto){
        var emailModel = new Email();
        BeanUtils.copyProperties(dto, emailModel);
        service.sendEmail(emailModel);
    }
}
