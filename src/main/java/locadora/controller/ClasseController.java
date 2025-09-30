package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.Classe;
import locadora.service.ClasseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@AllArgsConstructor
@Tag(
        name = "Classe",
        description = "Endpoints para gerenciamento de classes"
)
public class ClasseController {
    private final ClasseService classeService;

    @GetMapping
    public ResponseEntity<?> listarClasses(){
        return ResponseEntity.ok().body(classeService.listar());
    }

    @PostMapping
    public ResponseEntity<?> salvarClasse(@RequestBody Classe classe){
        classeService.salvar(classe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<?> atualizarClasse(@RequestBody Classe classe){
        classeService.atualizar(classe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarClasse(@PathVariable Long id){
        classeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
