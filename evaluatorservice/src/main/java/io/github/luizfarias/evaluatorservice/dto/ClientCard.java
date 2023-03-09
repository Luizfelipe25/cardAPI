package io.github.luizfarias.evaluatorservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}
