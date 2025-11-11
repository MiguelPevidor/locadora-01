package locadora.domain.dto.titulo;

import java.util.List;

public record TituloRequestDto(
    String nome,
    String ano,
    String sinopse,
    String categoria,
    List<Long> atores ,
    Long diretor,
    Long classe
) {
}
