package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.Ator;
import locadora.domain.Diretor;
import locadora.service.DiretorService;
import locadora.service.DiretorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diretores")
@AllArgsConstructor
@Tag(
        name = "Diretor",
        description = "Endpoints para gerenciamento de diretores"
)
public class DiretorController {
    private final DiretorService diretorService;

    @GetMapping("/listarDiretores")
    public ResponseEntity<?> listarDiretores(){
        return ResponseEntity.ok().body(diretorService.listar());
    }

    @PostMapping("/salvarDiretor")
    public ResponseEntity<?> salvarDiretor(@RequestBody Diretor diretor){
        diretorService.salvar(diretor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editarDiretor")
    public ResponseEntity<?> atualizarDiretor(@RequestBody Diretor diretor){
        diretorService.atualizar(diretor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarDiretor/{id}")
    public ResponseEntity<?> deletarDiretor(@PathVariable Long id){
        diretorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarDiretor/{id}")
    public ResponseEntity<Diretor> buscarAtorPorId(@PathVariable Long id) {
        Diretor diretor = diretorService.buscarPorId(id);
        return ResponseEntity.ok().body(diretor);
    }


}
