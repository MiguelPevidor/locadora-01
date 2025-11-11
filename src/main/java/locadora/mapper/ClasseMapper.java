package locadora.mapper;

import locadora.domain.Classe;
import locadora.domain.dto.classe.ClasseRequestDto;
import locadora.domain.dto.classe.ClasseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClasseMapper extends UtilMapper{

    ClasseResponseDto toDto(Classe entity);

    @Mapping(target="titulos", ignore = true)
    Classe toEntity(ClasseRequestDto dto);

    List<ClasseResponseDto> toDtoList(List<Classe> entities);

    void updateEntity(ClasseRequestDto dto, @MappingTarget Classe entity);
}
