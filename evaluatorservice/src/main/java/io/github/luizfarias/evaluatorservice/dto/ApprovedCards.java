package io.github.luizfarias.evaluatorservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCards {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
