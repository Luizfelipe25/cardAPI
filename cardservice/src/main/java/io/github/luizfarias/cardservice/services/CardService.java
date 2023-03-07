package io.github.luizfarias.cardservice.services;

import io.github.luizfarias.cardservice.domain.Card;
import io.github.luizfarias.cardservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getCardByRentLowerOrEqual(Long rent) {
        BigDecimal renda = BigDecimal.valueOf(rent);
        return cardRepository.findByRendaLessThanEqual(renda);
    }
}
