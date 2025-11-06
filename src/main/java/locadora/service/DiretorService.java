package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Diretor;
import locadora.domain.dto.DiretorDto;
import locadora.mapper.DiretorMapper;
import locadora.repository.DiretorRepository;
import locadora.repository.DiretorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiretorService {

    private final DiretorRepository repository;
    private final DiretorMapper mapper;

    public List<DiretorDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(DiretorDto diretor){
        repository.save(mapper.toEntity(diretor));
    }

    public void atualizar(DiretorDto diretor){
        if(diretor.id() == null) {
            throw new RuntimeException("Id do Diretor não pode ser nulo");
        }

        Diretor DiretorAtualizado = repository.findById(diretor.id()).orElseThrow(() -> new RuntimeException("Diretor não encontrado"));

        repository.save(mapper.toEntity(diretor));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }




    public DiretorDto buscarPorId(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado")));  // Lança uma exceção se não encontrar o ator
    }
}
