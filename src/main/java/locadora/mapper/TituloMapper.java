package locadora.mapper;

import locadora.domain.Titulo;
import locadora.domain.dto.titulo.TituloRequestDto;
import locadora.domain.dto.titulo.TituloResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses =  {ItemMapper.class})
public interface TituloMapper extends UtilMapper {

    @Mapping(target = "atores", ignore = true)
    @Mapping(target = "diretor", ignore = true)
    @Mapping(target = "classe", ignore = true)
    Titulo toEntity(TituloRequestDto dto);

//    @Mapping(target = "atores", qualifiedByName = "mapAtoresToIdList")
//    @Mapping(target = "diretor", source = "diretor", qualifiedByName = "mapDiretorToId")
//    @Mapping(target = "classe", source = "classe", qualifiedByName = "mapClasseToId")
    TituloResponseDto toDto(Titulo entity);

    List<TituloResponseDto> toDtoList(List<Titulo> entities);

    void updateEntity(Titulo dto, @MappingTarget Titulo entity);


}
