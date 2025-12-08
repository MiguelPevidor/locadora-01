package locadora.mapper;

import locadora.domain.Socio;
import locadora.domain.dto.socio.SocioRequestDto;
import locadora.domain.dto.socio.SocioResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocioMapper {

    Socio toEntity(SocioRequestDto dto);

    SocioResponseDto toDto(Socio entity);

    List<SocioResponseDto> toDtoList(List<Socio> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SocioRequestDto dto, @MappingTarget Socio entity);
}
