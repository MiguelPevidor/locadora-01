package locadora.domain.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import locadora.domain.Titulo;

import java.time.LocalDate;

public record ItemDto (
    Long id,
    String numSerie,
    LocalDate dtAquisicao,
    String tipoItem,
    Titulo titulo
){
}
