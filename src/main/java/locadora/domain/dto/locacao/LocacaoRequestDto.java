package locadora.domain.dto.locacao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LocacaoRequestDto(
        @NotNull
        Long idCliente,
        @NotNull
        Long idItem,
        Double valorCobrado,
        LocalDate dtDevolucaoPrevista
) {
}
