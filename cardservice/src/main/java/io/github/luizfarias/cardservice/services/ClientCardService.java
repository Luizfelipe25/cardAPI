package io.github.luizfarias.cardservice.services;

import io.github.luizfarias.cardservice.domain.ClientCard;
import io.github.luizfarias.cardservice.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return clientCardRepository.findByCpf(cpf);
    }

}
