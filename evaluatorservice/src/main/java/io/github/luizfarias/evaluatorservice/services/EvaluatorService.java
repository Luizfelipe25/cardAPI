package io.github.luizfarias.evaluatorservice.services;

import feign.FeignException;
import io.github.luizfarias.evaluatorservice.dto.*;
import io.github.luizfarias.evaluatorservice.exceptions.ClientDataNotFound;
import io.github.luizfarias.evaluatorservice.exceptions.MissMsComunicationException;
import io.github.luizfarias.evaluatorservice.infra.CardsClient;
import io.github.luizfarias.evaluatorservice.infra.ClientsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluatorService {

    private final ClientsClient clientsClient;
    private final CardsClient cardsClient;

    public ClientStatus getClientSituation(String cpf) throws ClientDataNotFound, MissMsComunicationException{
        try{
        ResponseEntity<ClientData> dadosCliente = clientsClient.clientData(cpf);
        ResponseEntity<List<ClientCard>> cardsByClient = cardsClient.getCardsByClient(cpf);

        return ClientStatus.builder()
                .cliente(dadosCliente.getBody())
                .cartoes(cardsByClient.getBody())
                .build();
        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFound();
            }
            throw new MissMsComunicationException(e.getMessage(),status);
        }
    }

    public ClientEvaluationResponse acomplishEvaluation (String cpf, Long renda) throws ClientDataNotFound, MissMsComunicationException{
        try{
        ResponseEntity<ClientData> dadosCliente = clientsClient.clientData(cpf);
        ResponseEntity<List<Card>> cardsByRent = cardsClient.getCardsByRent(renda);

            List<Card> cards = cardsByRent.getBody();


         List<ApprovedCards> cardsApproved = cards.stream().map(card -> {

                ClientData clientData = dadosCliente.getBody();

                BigDecimal limiteBasico = card.getLimiteBasico();
                BigDecimal rendaBd = BigDecimal.valueOf(renda);
                BigDecimal idadeBd = BigDecimal.valueOf(clientData.getIdade());

                var fator = idadeBd.divide(BigDecimal.valueOf(10));
                BigDecimal limiteaprovado = fator.multiply(limiteBasico);

                ApprovedCards approved = new ApprovedCards();
                approved.setCartao(card.getNome());
                approved.setBandeira(card.getBandeira());
                approved.setLimiteAprovado(limiteaprovado);
                return  approved;

            }).collect(Collectors.toList());

            return ClientEvaluationResponse.builder()
                    .cartoes(cardsApproved)
                    .build();

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFound();
            }
            throw new MissMsComunicationException(e.getMessage(),status);
        }
    }
}
