package locadora.service;

import locadora.domain.Diretor;
import locadora.repository.DiretorRepository;
import locadora.repository.DiretorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiretorService {

    private final DiretorRepository repository;

    public List<Diretor> listar(){
        return repository.findAll();
    }

    public void salvar(Diretor Diretor){
        repository.save(Diretor);
    }

    public void atualizar(Diretor Diretor){
        if(Diretor.getId() == null) {
            throw new RuntimeException("Id do Diretor não pode ser nulo");
        }

        Diretor DiretorAtualizado = repository.findById(Diretor.getId()).orElseThrow(() -> new RuntimeException("Diretor não encontrado"));

        repository.save(Diretor);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
