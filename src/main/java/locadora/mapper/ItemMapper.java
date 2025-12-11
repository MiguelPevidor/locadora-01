package locadora.mapper;

import locadora.domain.Item;
import locadora.domain.dto.item.ItemRequestDto;
import locadora.domain.dto.item.ItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "titulo.id", target = "tituloId")
    @Mapping(source = "titulo.nome", target = "nomeTitulo")
    ItemResponseDto toDto(Item entity);

    @Mapping(target = "titulo", ignore = true)
    Item toEntity(ItemRequestDto dto);


    List<ItemResponseDto> toDtoList(List<Item> entities);

    void updateEntity(Item dto, @MappingTarget Item entity);
}
