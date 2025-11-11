package locadora.domain.dto.classe;

import java.time.LocalDate;

public record ClasseRequestDto(
        String nome,
        Double valor,
        LocalDate prazoDevolucao
) {
}
