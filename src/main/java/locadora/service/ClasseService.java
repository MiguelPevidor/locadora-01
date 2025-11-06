package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.domain.dto.ClasseDto;
import locadora.mapper.ClasseMapper;
import locadora.repository.ClasseRepository;
import locadora.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClasseService {

    private final ClasseRepository repository;
    private final ClasseMapper mapper;

    public List<ClasseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(ClasseDto Classe){
        repository.save(mapper.toEntity(Classe));
    }

    public void atualizar(ClasseDto classe){
        if(classe.id() == null) {
            throw new RuntimeException("Id da Classe não pode ser nulo");
        }

        Classe ClasseAtualizado = repository.findById(classe.id()).orElseThrow(() -> new RuntimeException("Classe não encontrado"));

        repository.save(mapper.toEntity(classe));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public ClasseDto buscarPorId(Long id) {
        Classe classe = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe não encontrado"));  // Lança uma exceção se não encontrar o ator

        return mapper.toDto(classe);
    }

}
