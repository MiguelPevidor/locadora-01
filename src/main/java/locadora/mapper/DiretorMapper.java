package locadora.mapper;

import locadora.domain.Diretor;
import locadora.domain.dto.diretor.DiretorRequestDto;
import locadora.domain.dto.diretor.DiretorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiretorMapper extends UtilMapper{

//    @Mapping(target="titulos", source="titulos", qualifiedByName="tituloToIdList")
    DiretorResponseDto toDto(Diretor entity);

    @Mapping(target="titulos",ignore = true)
    Diretor toEntity(DiretorRequestDto dto);

    List<DiretorResponseDto> toDtoList(List<Diretor> entities);

    void updateEntity(DiretorRequestDto dto, @MappingTarget Diretor entity);
}
