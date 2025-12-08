package locadora.domain.dto.socio;

import locadora.domain.dto.dependente.DependenteResponseDto;

import java.time.LocalDate;
import java.util.List;

public record SocioResponseDto(
        String numInscricao,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        boolean estahAtivo,
        String cpf,
        String endereco,
        String telefone,
        List<DependenteResponseDto> dependentes
) {
}
