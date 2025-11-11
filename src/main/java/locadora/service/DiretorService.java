package locadora.service;

import locadora.domain.Diretor;
import locadora.domain.dto.diretor.DiretorRequestDto;
import locadora.domain.dto.diretor.DiretorResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.mapper.DiretorMapper;
import locadora.repository.DiretorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiretorService {

    private final DiretorRepository repository;
    private final DiretorMapper mapper;

    public List<DiretorResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(DiretorRequestDto diretor){
        repository.save(mapper.toEntity(diretor));
    }

    public void atualizar(Long id,DiretorRequestDto diretor){

        Diretor diretorEncontrado = buscarPorId(id);
        mapper.updateEntity(diretor,diretorEncontrado);
        repository.save(diretorEncontrado);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }




    public Diretor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Diretor não encontrado"));  // Lança uma exceção se não encontrar o ator
    }
}
