package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.dto.item.ItemRequestDto;
import locadora.domain.dto.item.ItemResponseDto;
import locadora.mapper.ItemMapper;
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
    private final ItemMapper itemMapper;

    @GetMapping("/listarItens")
    public ResponseEntity<?> listarItens(){

        return ResponseEntity.ok().body(itemService.listar());
    }

    @PostMapping("/salvarItem")
    public ResponseEntity<?> salvarItem(@RequestBody @Valid ItemRequestDto item){
        itemService.salvar(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarItem")
    public ResponseEntity<?> atualizarItem(@PathVariable Long id,@RequestBody @Valid ItemRequestDto item){
        itemService.atualizar(id,item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarItem/{id}")
    public ResponseEntity<?> deletarItem(@PathVariable Long id){
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarItem/{id}")
    public ResponseEntity<ItemResponseDto> buscarItemPorId(@PathVariable Long id) {
        ItemResponseDto item = itemMapper.toDto(itemService.buscarPorId(id));
        return ResponseEntity.ok().body(item);
    }
}
