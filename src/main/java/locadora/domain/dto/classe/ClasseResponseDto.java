package locadora.domain.dto.classe;

import java.time.LocalDate;
import java.util.List;

public record ClasseResponseDto(
    Long id,
    String nome,
    Double valor,
    int prazoDevolucao
) {
}
