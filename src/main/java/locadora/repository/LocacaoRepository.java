package locadora.repository;

import locadora.domain.Item;
import locadora.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List; // Importante
import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    boolean existsByItemAndDtDevolucaoEfetivaIsNull(Item item);

    Optional<Locacao> findByItemAndDtDevolucaoEfetivaIsNull(Item item);

    List<Locacao> findAllByDtDevolucaoEfetivaIsNull();
}