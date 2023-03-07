package io.github.luizfarias.cardservice.repository;

import io.github.luizfarias.cardservice.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard,Long> {
    List<ClientCard> findByCpf(String cpf);
}
