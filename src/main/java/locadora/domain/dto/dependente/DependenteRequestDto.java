package locadora.domain.dto.dependente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DependenteRequestDto(
        Long id,
        @NotNull@NotBlank
        String numInscricao,
        @NotNull@NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        String sexo) {
}
