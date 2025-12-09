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
        if(entity.getDependentes() != null) {
            for (Dependente dependente : entity.getDependentes()) {
                dependente.setEstahAtivo(true);
                dependente.setResponsavel(entity);
            }
        }
        repository.save(entity);
    }

    public void atualizar(Long id, SocioRequestDto dto) {
        Socio socio = buscarPorId(id);
        mapper.updateEntity(dto, socio);
        repository.save(socio);
    }

    public void inativar(Long id){
        Socio socio = buscarPorId(id);

        socio.setEstahAtivo(false);
        for(Dependente dependente: socio.getDependentes()){
            dependente.setEstahAtivo(false);
        }
        repository.save(socio);

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

        repository.save(socio);

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
