package io.github.luizfarias.cardservice.dto;


import io.github.luizfarias.cardservice.domain.Card;
import io.github.luizfarias.cardservice.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Card toCard(){
        return new Card(nome,bandeira,renda,limite);
    }
}
