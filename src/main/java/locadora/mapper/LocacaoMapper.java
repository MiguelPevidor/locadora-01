package locadora.mapper;

import locadora.domain.Ator;
import locadora.domain.Locacao;
import locadora.domain.Titulo;
import locadora.domain.dto.ator.AtorResponseDto;
import locadora.domain.dto.locacao.LocacaoRequestDto;
import locadora.domain.dto.locacao.LocacaoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {
    void updateEntity(LocacaoRequestDto dto, @MappingTarget Locacao entity);

    LocacaoResponseDto toDto(Locacao entity);


    List<LocacaoResponseDto> toDtoList(List<Locacao> entities);
}
