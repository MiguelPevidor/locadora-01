package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.domain.Diretor;
import locadora.domain.Titulo;
import locadora.domain.dto.titulo.TituloRequestDto;
import locadora.domain.dto.titulo.TituloResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.handler.exceptions.EntidadeNaoPodeSerExcluidaException;
import locadora.mapper.TituloMapper;
import locadora.repository.AtorRepository;
import locadora.repository.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TituloService {

    private final TituloRepository repository;
    private final TituloMapper mapper;
    private final AtorRepository atorRepository;
    private final DiretorService diretorService;
    private final ClasseService classeService;


    public List<TituloResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(TituloRequestDto dto){
        Titulo titulo = mapper.toEntity(dto);

        //buscar os atores
        List<Ator> atores = buscarAtoresPorIds(dto.atores());
        //buscar o diretor
        Diretor diretor = diretorService.buscarPorId(dto.diretor());
        //buscar a classe
        Classe classe = classeService.buscarPorId(dto.classe());

        //vincular todos ao titulo
        titulo.setAtores(atores);
        titulo.setDiretor(diretor);
        titulo.setClasse(classe);

        repository.save(titulo);
    }

    public void atualizar(Long id, TituloRequestDto titulo) {

        // Buscar a entidade Titulo existente
        Titulo tituloEncontrado = buscarPorId(id);

        // Buscar os atores
        List<Ator> atores = buscarAtoresPorIds(titulo.atores());
        // Buscar o diretor
        Diretor diretor = diretorService.buscarPorId(titulo.diretor());
        // Buscar a classe
        Classe classe = classeService.buscarPorId(titulo.classe());

        // Mapeie o DTO TituloRequestDto para uma entidade Titulo
        Titulo tituloAtualizado = mapper.toEntity(titulo); // Mapeamento do DTO para a entidade Titulo

        // Atualiza os dados da entidade encontrada com os dados do DTO
        tituloAtualizado.setAtores(atores);
        tituloAtualizado.setDiretor(diretor);
        tituloAtualizado.setClasse(classe);

        // Atualize a entidade encontrada com os novos dados
        mapper.updateEntity(tituloAtualizado, tituloEncontrado);

        // Salve a entidade atualizada, preservando o ID
        repository.save(tituloEncontrado);
    }



    private List<Ator> buscarAtoresPorIds(List<Long> ids){
        List<Ator> atoresEncontrados = atorRepository.findAllById(ids);

        if (atoresEncontrados.size() != ids.size()) {
            throw new EntidadeNaoEncontradaException("Um ou mais atores não foram encontrados");
        }

        return atoresEncontrados;
    }

    public void deletar(Long id){

        if(tituloPossuiItens(id)) {
            throw new EntidadeNaoPodeSerExcluidaException("Não é possível deletar o título pois ele possuí um ou mais itens.");
        }
        repository.deleteById(id);
    }

    private boolean tituloPossuiItens(Long id) {
        Titulo titulo = buscarPorId(id);

        if(!titulo.getItens().isEmpty()) {
            //Titulo possui itens pois a lista de itens não está vazia
            return true;
        }

        return false;
    }


    public Titulo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("titulo não encontrado"));  // Lança uma exceção se não encontrar o titulo
    }

    public List<Titulo> buscarTituloPorIds(List<Long> titulos) {
        List<Titulo> titulosEncontrados = repository.findAllById(titulos);

        if (titulosEncontrados.size() != titulos.size()) {
            throw new EntidadeNaoEncontradaException("Um ou mais titulos não foram encontrados");
        }

        return titulosEncontrados;
    }
}
