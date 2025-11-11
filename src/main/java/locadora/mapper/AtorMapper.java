package locadora.mapper;

import locadora.domain.Ator;
import locadora.domain.dto.ator.AtorRequestDto;
import locadora.domain.dto.ator.AtorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtorMapper extends UtilMapper{

    @Mapping(target="titulos", ignore = true)
    Ator toEntity(AtorRequestDto dto);

    AtorResponseDto toDto(Ator entity);

    List<AtorResponseDto> toDtoList(List<Ator> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(AtorRequestDto dto, @MappingTarget Ator entity);
}
