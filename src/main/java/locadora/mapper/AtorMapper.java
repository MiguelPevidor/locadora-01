package locadora.mapper;

import locadora.domain.Ator;
import locadora.domain.Titulo;
import locadora.domain.dto.AtorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtorMapper extends UtilMapper{

    @Mapping(target="titulos", ignore = true)
    Ator toEntity(AtorDto dto);

    @Mapping(target="titulos", source="titulos", qualifiedByName="tituloIdList")
    AtorDto toDto(Ator entity);

    List<AtorDto> toDtoList(List<Ator> entities);
}
