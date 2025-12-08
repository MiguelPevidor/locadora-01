package locadora.service;

import locadora.domain.Dependente;
import locadora.domain.Socio;
import locadora.domain.dto.socio.SocioRequestDto;
import locadora.domain.dto.socio.SocioResponseDto;
import locadora.handler.exceptions.BusinessException;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.handler.exceptions.EntidadeNaoPodeSerExcluidaException;
import locadora.mapper.SocioMapper;
import locadora.repository.SocioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SocioService {
    private final SocioRepository repository;
    private final SocioMapper mapper;

    public List<SocioResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(SocioRequestDto socio){

        if(socio.dependentes().size() > 3){
            throw new BusinessException("Um sócio não pode ter mais de 3 dependentes");
        }
        Socio entity = mapper.toEntity(socio);
        entity.setEstahAtivo(true);
        repository.save(entity);
    }

    public void atualizar(Long id,SocioRequestDto socio){

        buscarPorId(id);
        Socio entity = mapper.toEntity(socio);
        repository.save(entity);
    }

    public void inativar(Long id){
        Socio socio = buscarPorId(id);

        socio.setEstahAtivo(false);
        for(Dependente dependente: socio.getDependentes()){
            dependente.setEstahAtivo(false);
        }

    }

    public void reativar(Long id){
        Socio socio = buscarPorId(id);

        socio.setEstahAtivo(true);
        int dependentesReativados = 0;

        for (Dependente dependente : socio.getDependentes()) {
            if (dependentesReativados < 3) {
                dependente.setEstahAtivo(true);
                dependentesReativados++;
            } else {
                break;
            }
        }

    }

    public Socio buscarPorId(Long id) {
        Socio socio = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Socio não encontrado"));  // Lança uma exceção se não encontrar o ator

        return socio;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
