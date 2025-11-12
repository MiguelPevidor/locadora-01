package locadora.service;

import locadora.domain.Classe;
import locadora.domain.dto.classe.ClasseRequestDto;
import locadora.domain.dto.classe.ClasseResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.handler.exceptions.EntidadeNaoPodeSerExcluidaException;
import locadora.mapper.ClasseMapper;
import locadora.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClasseService {

    private final ClasseRepository repository;
    private final ClasseMapper mapper;

    public List<ClasseResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(ClasseRequestDto classe){

        Classe entity = mapper.toEntity(classe);
        repository.save(entity);
    }

    public void atualizar(Long id,ClasseRequestDto classe){

        buscarPorId(id);
        Classe entity = mapper.toEntity(classe);
        repository.save(entity);
    }

    public void deletar(Long id){
        if(isClasseRelacionadoATitulos(id)) {
            throw new EntidadeNaoPodeSerExcluidaException("Não é possível deletar a classe pois ela está relacionada a um ou mais títulos.");
        }
        repository.deleteById(id);
    }

    private boolean isClasseRelacionadoATitulos(Long id) {
        Classe classe = buscarPorId(id);

        if(!classe.getTitulos().isEmpty()) {
            //Classe está relacionado a títulos pois a lista de títulos não está vazia
            return true;
        }

        //classe não está relacionado a nenhum título
        return false;
    }


    public Classe buscarPorId(Long id) {
        Classe classe = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Classe não encontrado"));  // Lança uma exceção se não encontrar o ator

        return classe;
    }

}
