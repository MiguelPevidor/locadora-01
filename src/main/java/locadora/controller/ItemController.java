package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.Diretor;
import locadora.domain.Item;
import locadora.domain.dto.ItemDto;
import locadora.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
@AllArgsConstructor
@Tag(
        name = "Item",
        description = "Endpoints para gerenciamento dos Itens"
)
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/listarItens")
    public ResponseEntity<?> listarItens(){

        return ResponseEntity.ok().body(itemService.listar());
    }

    @PostMapping("/salvarItem")
    public ResponseEntity<?> salvarItem(@RequestBody ItemDto item){
        itemService.salvar(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editarItem")
    public ResponseEntity<?> atualizarItem(@RequestBody ItemDto item){
        itemService.atualizar(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarItem/{id}")
    public ResponseEntity<?> deletarItem(@PathVariable Long id){
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarItem/{id}")
    public ResponseEntity<ItemDto> buscarItemPorId(@PathVariable Long id) {
        ItemDto item = itemService.buscarPorId(id);
        return ResponseEntity.ok().body(item);
    }
}
