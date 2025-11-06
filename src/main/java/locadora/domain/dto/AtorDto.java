package locadora.domain.dto;

import jakarta.persistence.ManyToMany;
import locadora.domain.Titulo;

import java.util.ArrayList;
import java.util.List;

public record AtorDto(
        Long id,
        String nome,
        List<Long> titulos
) {

}
