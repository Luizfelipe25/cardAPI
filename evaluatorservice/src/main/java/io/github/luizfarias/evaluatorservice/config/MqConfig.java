package io.github.luizfarias.evaluatorservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesQueue;
    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesQueue,true);
    }
}
