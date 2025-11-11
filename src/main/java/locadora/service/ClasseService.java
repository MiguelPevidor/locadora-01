package locadora.service;

import locadora.domain.Classe;
import locadora.domain.dto.classe.ClasseRequestDto;
import locadora.domain.dto.classe.ClasseResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
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
        repository.deleteById(id);
    }


    public Classe buscarPorId(Long id) {
        Classe classe = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Classe não encontrado"));  // Lança uma exceção se não encontrar o ator

        return classe;
    }

}
