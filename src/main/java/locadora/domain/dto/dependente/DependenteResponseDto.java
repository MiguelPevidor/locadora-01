package locadora.domain.dto.dependente;

import java.time.LocalDate;

public record DependenteResponseDto(
        Long id,
        String numInscricao,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        boolean estahAtivo
) {
}
