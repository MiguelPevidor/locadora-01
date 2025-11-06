package locadora.service;

import locadora.domain.Item;
import locadora.domain.dto.ItemDto;
import locadora.mapper.ItemMapper;
import locadora.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    public List<ItemDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(ItemDto item){
        repository.save(mapper.toEntity(item));
    }

    public void atualizar(ItemDto item){
        if(item.id() == null) {
            throw new RuntimeException("Id do item não pode ser nulo");
        }

        Item itemAtualizado = repository.findById(item.id()).orElseThrow(() -> new RuntimeException("item não encontrado"));

        repository.save(mapper.toEntity(item));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public ItemDto buscarPorId(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado")));  // Lança uma exceção se não encontrar o Item
    }
}
