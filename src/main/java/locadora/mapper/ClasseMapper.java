package locadora.mapper;

import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.domain.dto.AtorDto;
import locadora.domain.dto.ClasseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClasseMapper extends UtilMapper{

    @Mapping(target="titulos", source="titulos", qualifiedByName="tituloIdList")
    ClasseDto toDto(Classe entity);

    @Mapping(target="titulos", ignore = true)
    Classe toEntity(ClasseDto dto);

    List<ClasseDto> toDtoList(List<Classe> entities);
}
