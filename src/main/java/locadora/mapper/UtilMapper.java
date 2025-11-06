package locadora.mapper;

import locadora.domain.Titulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilMapper {

    @Named("tituloIdList")
    default List<Long> mapTituloIdList(List<Titulo> titulos){
        return titulos.stream()
                .map(Titulo::getId)
                .toList();
    }
}
