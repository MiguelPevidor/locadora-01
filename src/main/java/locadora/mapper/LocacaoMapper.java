package locadora.mapper;

import locadora.domain.Locacao;
import locadora.domain.dto.locacao.LocacaoRequestDto;
import locadora.domain.dto.locacao.LocacaoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {
    void updateEntity(LocacaoRequestDto dto, @MappingTarget Locacao entity);

    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "nomeCliente", source = "cliente.nome")
    @Mapping(target = "numInscricaoCliente", source = "cliente.numInscricao")

    @Mapping(target = "idItem", source = "item.id")
    @Mapping(target = "tituloItem", source = "item.titulo.nome")
    @Mapping(target = "numSerieItem", source = "item.numSerie")
    @Mapping(target = "tipoItem", source = "item.tipoItem")
    LocacaoResponseDto toDto(Locacao entity);

    List<LocacaoResponseDto> toDtoList(List<Locacao> entities);
}