package io.github.luizfarias.cardservice.repository;

import io.github.luizfarias.cardservice.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByRendaLessThanEqual(BigDecimal renda);
}

