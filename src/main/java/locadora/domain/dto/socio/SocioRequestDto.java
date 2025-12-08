package locadora.domain.dto.socio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import locadora.domain.dto.dependente.DependenteRequestDto;
import java.time.LocalDate;
import java.util.List;

public record SocioRequestDto(
        @NotNull@NotBlank
        String numInscricao,
        @NotNull@NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        String sexo,
        @NotNull@NotBlank
        String cpf,
        String endereco,
        @NotNull@NotBlank
        String telefone,
        List<DependenteRequestDto> dependentes
) {
}
