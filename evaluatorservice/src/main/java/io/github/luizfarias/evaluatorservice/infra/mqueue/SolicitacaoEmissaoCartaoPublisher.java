package io.github.luizfarias.evaluatorservice.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import io.github.luizfarias.evaluatorservice.dto.RequestEmissionCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;

    public void solicitarCartao(RequestEmissionCardData dados) throws JsonProcessingException {
        var json = convertToJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(),json);

    }
    private String convertToJson(RequestEmissionCardData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
