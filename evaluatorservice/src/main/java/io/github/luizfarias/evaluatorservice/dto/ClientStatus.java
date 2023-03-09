package io.github.luizfarias.evaluatorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientStatus {

    private ClientData cliente;
    private List<ClientCard> cartoes;
}
