package locadora.domain.dto.dependente;

import java.time.LocalDate;

public record DependenteResponseDto(
        String numInscricao,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        boolean estahAtivo,
        String cpf,
        String endereco,
        String telefone
) {
}
