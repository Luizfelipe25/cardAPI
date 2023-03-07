package io.github.luizfarias.cardservice.resource;


import io.github.luizfarias.cardservice.domain.Card;
import io.github.luizfarias.cardservice.domain.ClientCard;
import io.github.luizfarias.cardservice.dto.CardSaveRequest;
import io.github.luizfarias.cardservice.dto.CardsPerClientResponse;
import io.github.luizfarias.cardservice.services.CardService;
import io.github.luizfarias.cardservice.services.ClientCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "Ok";
    }

    @GetMapping(params = "rent")
    public ResponseEntity<List<Card>> getCardsByRent(@RequestParam Long rent){
        List<Card> cardByRentLowerOrEqual = cardService.getCardByRentLowerOrEqual(rent);
        return ResponseEntity.ok(cardByRentLowerOrEqual);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity registry (@RequestBody CardSaveRequest request) {
        Card card = request.toCard();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerClientResponse>> getCardsByClient(@RequestParam("cpf") String cpf){
        List<ClientCard> clientCards = clientCardService.listCardsByCpf(cpf);
        List<CardsPerClientResponse> cardsPerClientResponse = clientCards.stream().map(CardsPerClientResponse::toCardPerClient).collect(Collectors.toList());
        return ResponseEntity.ok(cardsPerClientResponse);
    }

}
