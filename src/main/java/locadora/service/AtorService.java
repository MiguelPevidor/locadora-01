package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Titulo;
import locadora.domain.dto.ator.AtorRequestDto;
import locadora.domain.dto.ator.AtorResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.mapper.AtorMapper;
import locadora.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AtorService {


    private final AtorRepository repository;
    private final AtorMapper mapper;
    private final TituloService tituloService;

    public List<AtorResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(AtorRequestDto ator){

        //não precisa passar os titulos aqui, a vinculação será feita no serviço de títulos
        Ator atorEntity = mapper.toEntity(ator);
        repository.save(atorEntity);
    }

    public void atualizar(Long id, AtorRequestDto ator){

        Ator atorEncontrado = buscarPorId(id);
        mapper.updateEntity(ator, atorEncontrado);
        repository.save(atorEncontrado);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public Ator buscarPorId(Long id) {
        Ator ator = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ator não encontrado"));

        return ator;// Lança uma exceção se não encontrar o ator
    }


    public List<Ator> buscarAtoresPorIds(List<Long> idsAtores) {
        List<Ator> atoresEncontrados = repository.findAllById(idsAtores);

        if (atoresEncontrados.size() != idsAtores.size()) {
            throw new EntidadeNaoEncontradaException("Um ou mais atores não foram encontrados");
        }

        return atoresEncontrados;
    }
}
