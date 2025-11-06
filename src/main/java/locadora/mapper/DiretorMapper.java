package locadora.mapper;

import locadora.domain.Classe;
import locadora.domain.Diretor;
import locadora.domain.dto.ClasseDto;
import locadora.domain.dto.DiretorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiretorMapper extends UtilMapper{

    @Mapping(target="titulos", source="titulos", qualifiedByName="tituloIdList")
    DiretorDto toDto(Diretor entity);

    @Mapping(target="titulos",ignore = true)
    Diretor toEntity(DiretorDto dto);

    List<DiretorDto> toDtoList(List<Diretor> entities);
}
