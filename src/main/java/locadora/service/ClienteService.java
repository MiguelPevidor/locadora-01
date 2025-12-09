package locadora.service;

import locadora.domain.Ator;
import locadora.domain.Cliente;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository repository;

    public Cliente buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        return cliente;// Lança uma exceção se não encontrar o Cliente
    }

}
