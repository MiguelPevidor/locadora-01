package locadora.domain.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import locadora.domain.Titulo;

import java.util.ArrayList;
import java.util.List;

public record DiretorDto(
        Long id,
        String nome,
        List<Long> titulos
) {
}
