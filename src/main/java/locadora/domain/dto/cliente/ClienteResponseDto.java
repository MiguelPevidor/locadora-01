package locadora.domain.dto.cliente;

import java.time.LocalDate;

public record ClienteResponseDto (
        Long id,
        String numInscricao,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        boolean estahAtivo,
        String cpf,
        String endereco,
        String telefone
){
}
