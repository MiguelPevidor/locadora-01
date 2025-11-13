package locadora.mapper;

import locadora.domain.Titulo;
import locadora.domain.dto.titulo.TituloRequestDto;
import locadora.domain.dto.titulo.TituloResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface TituloMapper {

    // Mapeia o DTO (TituloRequestDto) para a entidade Titulo
    @Mapping(target = "atores", ignore = true)
    @Mapping(target = "diretor", ignore = true)
    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "id", ignore = true) // Ignora o mapeamento do ID, já que não deve ser alterado
    Titulo toEntity(TituloRequestDto dto);

    // Mapeia a entidade Titulo para o DTO TituloResponseDto
    TituloResponseDto toDto(Titulo entity);

    // Mapeia uma lista de Titulo para uma lista de TituloResponseDto
    List<TituloResponseDto> toDtoList(List<Titulo> entities);

    // Atualiza os campos da entidade Titulo com os dados do DTO
    @Mapping(target = "id", ignore = true) // Ignora a atualização do campo ID
    void updateEntity(Titulo dto, @MappingTarget Titulo entity);
}


