package locadora.mapper;

import locadora.domain.Dependente;
import locadora.domain.dto.dependente.DependenteRequestDto;
import locadora.domain.dto.dependente.DependenteResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DependenteMapper {
    Dependente toEntity(DependenteRequestDto dto);

    DependenteResponseDto toDto(Dependente entity);

    List<DependenteResponseDto> toDtoList(List<Dependente> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(DependenteRequestDto dto, @MappingTarget Dependente entity);
}
