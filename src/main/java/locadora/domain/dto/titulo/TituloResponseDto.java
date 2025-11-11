package locadora.domain.dto.titulo;

import locadora.domain.dto.ator.AtorResponseDto;
import locadora.domain.dto.classe.ClasseResponseDto;
import locadora.domain.dto.diretor.DiretorResponseDto;
import locadora.domain.dto.item.ItemResponseDto;

import java.util.List;

public record TituloResponseDto(
        Long id,
        String nome,
        String ano,
        String sinopse,
        String categoria,
        List<AtorResponseDto> atores ,
        DiretorResponseDto diretor,
        ClasseResponseDto classe,
        List<ItemResponseDto> itens
) {
}
