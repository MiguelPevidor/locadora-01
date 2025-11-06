package locadora.service;

import locadora.domain.Ator;
import locadora.domain.dto.AtorDto;
import locadora.mapper.AtorMapper;
import locadora.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtorService {


    private final AtorRepository repository;
    private final AtorMapper mapper;

    public List<AtorDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(AtorDto ator){

        repository.save(mapper.toEntity(ator));
    }

    public void atualizar(AtorDto ator){
        if(ator.id() == null) {
            throw new RuntimeException("Id do ator não pode ser nulo");
        }

        Ator atorAtualizado = repository.findById(ator.id()).orElseThrow(() -> new RuntimeException("Ator não encontrado"));

        repository.save(mapper.toEntity(ator));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public AtorDto buscarPorId(Long id) {
        Ator ator = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ator não encontrado"));

        return mapper.toDto(ator);// Lança uma exceção se não encontrar o ator
    }


}
