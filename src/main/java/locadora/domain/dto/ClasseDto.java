package locadora.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record ClasseDto(
    Long id,
    String nome,
    Double valor,
    LocalDate prazoDevolucao,
    List<Long> titulos
) {
}
