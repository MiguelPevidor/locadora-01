package locadora.service;

import locadora.domain.Item;
import locadora.domain.Titulo;
import locadora.domain.dto.item.ItemRequestDto;
import locadora.domain.dto.item.ItemResponseDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
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
    private final TituloService tituloService;

    public List<ItemResponseDto> listar(){
        return mapper.toDtoList(repository.findAll());
    }

    public void salvar(ItemRequestDto item){
        //buscar Titulo no banco de dados
        Titulo titulo = tituloService.buscarPorId(item.titulo());
        Item entity = mapper.toEntity(item);
        entity.setTitulo(titulo);

        repository.save(entity);
    }

    public void atualizar(Long id,ItemRequestDto item){

        Item itemEncontrado = buscarPorId(id);

        Titulo titulo = tituloService.buscarPorId(item.titulo());

        Item entity = mapper.toEntity(item);
        entity.setTitulo(titulo);

        mapper.updateEntity(entity,itemEncontrado);
        repository.save(itemEncontrado);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


    public Item buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item não encontrado"));  // Lança uma exceção se não encontrar o Item
    }
}
