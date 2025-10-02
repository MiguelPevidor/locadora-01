package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Classe;
import locadora.repository.ClasseRepository;
import locadora.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClasseService {

    private final ClasseRepository repository;

    public List<Classe> listar(){
        return repository.findAll();
    }

    public void salvar(Classe Classe){
        repository.save(Classe);
    }

    public void atualizar(Classe Classe){
        if(Classe.getId() == null) {
            throw new RuntimeException("Id do Classe não pode ser nulo");
        }

        Classe ClasseAtualizado = repository.findById(Classe.getId()).orElseThrow(() -> new RuntimeException("Classe não encontrado"));

        repository.save(Classe);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public Classe buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe não encontrado"));  // Lança uma exceção se não encontrar o ator
    }

}
