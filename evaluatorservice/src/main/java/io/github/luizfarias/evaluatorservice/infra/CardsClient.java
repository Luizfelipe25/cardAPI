package io.github.luizfarias.evaluatorservice.infra;

import io.github.luizfarias.evaluatorservice.dto.Card;
import io.github.luizfarias.evaluatorservice.dto.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "cardservice", path = "/cartoes")
public interface CardsClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "rent")
    ResponseEntity<List<Card>> getCardsByRent(@RequestParam Long rent);
}
