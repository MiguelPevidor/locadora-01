package locadora.mapper;

import locadora.domain.Locacao;
import locadora.domain.Titulo;
import locadora.domain.dto.locacao.LocacaoRequestDto;
import locadora.domain.dto.locacao.LocacaoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {
    void updateEntity(LocacaoRequestDto dto, @MappingTarget Locacao entity);

    LocacaoResponseDto toDto(Locacao entity);
}
