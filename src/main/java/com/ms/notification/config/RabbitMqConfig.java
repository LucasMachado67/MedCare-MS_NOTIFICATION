package com.ms.notification.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Configuração da integração com o RabbitMQ.
 *
 * <p>Esta classe registra os beans essenciais para o funcionamento da fila
 * utilizada no sistema de notificação, incluindo:
 *
 * <ul>
 *   <li>A criação da fila de e-mails.</li>
 *   <li>A conversão de mensagens para JSON via {@link Jackson2JsonMessageConverter}.</li>
 * </ul>
 *
 * <p>As configurações são carregadas a partir do arquivo de propriedades
 * (application.properties / application.yml), utilizando a chave:
 * <strong>broker.queue.email.name</strong>.
 */
@Configuration
public class RabbitMqConfig {

    /**
     * Nome da fila utilizada para processamento de e-mails.
     * O valor é carregado dinamicamente das configurações da aplicação.
     */
    @Value("${broker.queue.email.name}")
    private String queue;
    /**
     * Cria e registra a fila utilizada para envio de mensagens relacionadas a e-mails.
     *
     * <p>A fila é criada como "durável", ou seja, permanece ativa mesmo se o
     * RabbitMQ reiniciar.
     *
     * @return objeto {@link Queue} configurado com o nome informado no properties.
     */
    @Bean
    public Queue queue(){
        return new Queue(queue, true);
    }
    /**
     * Define o conversor de mensagens que será utilizado pelo RabbitMQ para
     * transformar mensagens JSON em objetos Java e vice-versa.
     *
     * <p>Utiliza o {@link ObjectMapper} do Jackson internamente.
     *
     * @return instância do conversor {@link Jackson2JsonMessageConverter}.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper(); 
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
