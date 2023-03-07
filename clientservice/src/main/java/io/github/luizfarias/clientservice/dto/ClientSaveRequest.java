package io.github.luizfarias.clientservice.dto;

import io.github.luizfarias.clientservice.domain.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Client toClient(){
        return Client.builder()
                .cpf(cpf)
                .nome(nome)
                .idade(idade)
                .build();
    }
}
