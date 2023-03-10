package io.github.luizfarias.evaluatorservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestEmissionCardData {

    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
