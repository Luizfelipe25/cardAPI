package io.github.luizfarias.cardservice.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.luizfarias.cardservice.domain.Card;
import io.github.luizfarias.cardservice.domain.ClientCard;
import io.github.luizfarias.cardservice.dto.RequestEmissionCardData;
import io.github.luizfarias.cardservice.repository.CardRepository;
import io.github.luizfarias.cardservice.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@RequiredArgsConstructor
public class CardIssueSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        try{
           var mapper = new ObjectMapper();
            RequestEmissionCardData data = mapper.readValue(payload, RequestEmissionCardData.class);
            Card card = cardRepository.findById(data.getIdCartao()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCartao(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setLimite(data.getLimiteLiberado());

            clientCardRepository.save(clientCard);

        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
