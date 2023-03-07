package io.github.luizfarias.cardservice.dto;

import io.github.luizfarias.cardservice.domain.Card;
import io.github.luizfarias.cardservice.domain.ClientCard;
import io.github.luizfarias.cardservice.enums.BandeiraCartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsPerClientResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public static CardsPerClientResponse toCardPerClient(ClientCard client){
        return new CardsPerClientResponse(
                client.getCartao().getNome(),
                client.getCartao().getBandeira().toString(),
                client.getLimite()
                );
    }
}
