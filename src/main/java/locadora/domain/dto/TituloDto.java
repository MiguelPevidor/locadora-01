package locadora.domain.dto;

import jakarta.persistence.*;
import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.domain.Diretor;
import locadora.domain.Item;

import java.util.ArrayList;
import java.util.List;

public record TituloDto(
    Long id,
    String nome,
    String ano,
    String sinopse,
    String categoria,
    List<AtorDto> atores ,
    DiretorDto diretor,
    ClasseDto classe,
    List<ItemDto> itens
) {
}
