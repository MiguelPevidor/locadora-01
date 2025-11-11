package locadora.domain.dto.item;

import java.time.LocalDate;

public record ItemRequestDto(
        String numSerie,
        LocalDate dtAquisicao,
        String tipoItem,
        Long titulo
) {
}
