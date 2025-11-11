package locadora.mapper;

import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.domain.Diretor;
import locadora.domain.Titulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilMapper {

    @Named("tituloToIdList")
    default List<Long> mapEntityIdList(List<Titulo> titulos){
        return titulos.stream()
                .map(Titulo::getId)
                .toList();
    }

    @Named("mapDiretorToId")
    default Long mapClasseToId(Diretor diretor){
        return diretor.getId();
    }

    @Named("mapClasseToId")
    default Long mapClasseToId(Classe classe){
        return classe.getId();
    }

    @Named("mapAtoresToIdList")
    default List<Long> mapAtoresToIdList(List<Ator> atores){
        return atores.stream()
                .map(Ator::getId)
                .toList();
    }

}
