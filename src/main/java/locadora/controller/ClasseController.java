package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.Ator;
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

    @GetMapping("/listarClasses")
    public ResponseEntity<?> listarClasses(){
        return ResponseEntity.ok().body(classeService.listar());
    }

    @PostMapping("/salvarClasse")
    public ResponseEntity<?> salvarClasse(@RequestBody Classe classe){
        classeService.salvar(classe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editarClasse")
    public ResponseEntity<?> atualizarClasse(@RequestBody Classe classe){
        classeService.atualizar(classe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarClasse/{id}")
    public ResponseEntity<?> deletarClasse(@PathVariable Long id){
        classeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarclasse/{id}")
    public ResponseEntity<Classe> buscarAtorPorId(@PathVariable Long id) {
        Classe classe = classeService.buscarPorId(id);
        return ResponseEntity.ok().body(classe);
    }


}
