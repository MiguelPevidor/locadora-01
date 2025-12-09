package locadora.service;

import locadora.domain.Dependente;
import locadora.domain.Socio;
import locadora.domain.dto.dependente.DependenteRequestDto;
import locadora.domain.dto.dependente.DependenteResponseDto;
import locadora.handler.exceptions.BusinessException;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.handler.exceptions.EntidadeNaoPodeSerExcluidaException;
import locadora.mapper.DependenteMapper;
import locadora.repository.DependenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DependenteService {
    private final DependenteRepository repository;
    private final SocioService socioService;
    private final DependenteMapper mapper;

    public List<DependenteResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(Long idSocio, DependenteRequestDto dependente){
        Socio socio = socioService.buscarPorId(idSocio);
        if(repository.countByResponsavel_IdAndEstahAtivoTrue(idSocio) >2 ){
            throw new BusinessException("Um Sócio não pode ter mais de 3 dependentes!");
        }

        Dependente entity = mapper.toEntity(dependente);
        entity.setEstahAtivo(true);
        repository.save(entity);
    }

    public void atualizar(Long id,DependenteRequestDto dependente){

        buscarPorId(id);
        Dependente entity = mapper.toEntity(dependente);
        repository.save(entity);
    }

    public void inativar(Long id){
        Dependente dependente = buscarPorId(id);
        dependente.setEstahAtivo(false);
        repository.save(dependente);
    }

    public void reativar(Long id){
        Dependente dependente = buscarPorId(id);

        if(dependente.getResponsavel().isEstahAtivo()) {
            dependente.setEstahAtivo(true);
        }else{
            throw new BusinessException("Não foi possivel reativar esse dependente, seu responsavel esta Inativo");
        }
    }




    public Dependente buscarPorId(Long id) {
        Dependente dependente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Dependente não encontrado"));  // Lança uma exceção se não encontrar
        return dependente;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
