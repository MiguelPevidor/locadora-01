package locadora.domain.dto.locacao;

import java.time.LocalDate;

public record LocacaoResponseDto(
        Long id,
        LocalDate dtLocacao,
        LocalDate dtDevolucaoPrevista,
        LocalDate dtDevolucaoEfetiva,
        Double valorCobrado,
        Double multaCobrada,

        Long idCliente,
        String nomeCliente,
        String numInscricaoCliente,

        Long idItem,
        String tituloItem,
        String numSerieItem,
        String tipoItem
) {
}