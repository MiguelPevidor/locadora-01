package locadora.domain.dto.item;

import java.time.LocalDate;

public record ItemResponseDto(
    Long id,
    String numSerie,
    LocalDate dtAquisicao,
    String tipoItem,
    Long tituloId,
    String nomeTitulo

){
}
