package locadora.mapper;

import locadora.domain.Diretor;
import locadora.domain.Item;
import locadora.domain.dto.DiretorDto;
import locadora.domain.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item entity);

    Item toEntity(ItemDto dto);

    List<ItemDto> toDtoList(List<Item> entities);
}
