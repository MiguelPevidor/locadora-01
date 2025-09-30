package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.Ator;
import locadora.service.AtorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atores")
@AllArgsConstructor
@Tag(
        name = "Atores",
        description = "Endpoints para gerenciamento de atores"
)
public class AtorController {

    private final AtorService atorService;

    @GetMapping
    public ResponseEntity<?> listarAtores(){
       return ResponseEntity.ok().body(atorService.listar());
    }

    @PostMapping
    public ResponseEntity<?> salvarAtor(@RequestBody Ator ator){
        atorService.salvar(ator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<?> atualizarAtor(@RequestBody Ator ator){
        atorService.atualizar(ator);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAtor(@PathVariable Long id){
        atorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
