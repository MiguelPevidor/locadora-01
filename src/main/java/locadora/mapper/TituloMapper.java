package locadora.mapper;

import locadora.domain.Titulo;
import locadora.domain.dto.AtorDto;
import locadora.domain.dto.TituloDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses =  {AtorMapper.class, DiretorMapper.class, ClasseMapper.class, ItemMapper.class})
public interface TituloMapper {

    Titulo toEntity(TituloDto dto);

    TituloDto toDto(Titulo entity);

    List<TituloDto> toDtoList(List<Titulo> entities);


}
