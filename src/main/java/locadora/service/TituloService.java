package locadora.service;

import locadora.domain.Titulo;
import locadora.domain.dto.TituloDto;
import locadora.mapper.TituloMapper;
import locadora.repository.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TituloService {

    private final TituloRepository repository;
    private final TituloMapper mapper;

    public List<TituloDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(TituloDto titulo){
        repository.save(mapper.toEntity(titulo));
    }

    public void atualizar(TituloDto titulo){
        if(titulo.id() == null) {
            throw new RuntimeException("Id do titulo não pode ser nulo");
        }

        Titulo tituloAtualizado = repository.findById(titulo.id()).orElseThrow(() -> new RuntimeException("titulo não encontrado"));

        repository.save(mapper.toEntity(titulo));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public TituloDto buscarPorId(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("titulo não encontrado")));  // Lança uma exceção se não encontrar o titulo
    }
}
