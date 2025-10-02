package locadora.service;

import locadora.domain.Ator;
import locadora.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtorService {

    private final AtorRepository repository;

    public List<Ator> listar(){
        return repository.findAll();
    }

    public void salvar(Ator ator){
        repository.save(ator);
    }

    public void atualizar(Ator ator){
        if(ator.getId() == null) {
            throw new RuntimeException("Id do ator não pode ser nulo");
        }

        Ator atorAtualizado = repository.findById(ator.getId()).orElseThrow(() -> new RuntimeException("Ator não encontrado"));

        repository.save(ator);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public Ator buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ator não encontrado"));  // Lança uma exceção se não encontrar o ator
    }


}
