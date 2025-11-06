package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.domain.dto.TituloDto;
import locadora.domain.dto.TituloDto;
import locadora.service.TituloService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titulos")
@AllArgsConstructor
@Tag(
        name = "Titulo",
        description = "Endpoints para gerenciamento dos Titulos"
)
public class TituloController {

    private final TituloService tituloService;

    @GetMapping("/listarTitulos")
    public ResponseEntity<?> listarTitulos(){

        return ResponseEntity.ok().body(tituloService.listar());
    }

    @PostMapping("/salvarTitulo")
    public ResponseEntity<?> salvarTitulo(@RequestBody TituloDto titulo){
        tituloService.salvar(titulo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editarTitulo")
    public ResponseEntity<?> atualizarTitulo(@RequestBody TituloDto titulo){
        tituloService.atualizar(titulo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarTitulo/{id}")
    public ResponseEntity<?> deletarTitulo(@PathVariable Long id){
        tituloService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarTitulo/{id}")
    public ResponseEntity<TituloDto> buscarTituloPorId(@PathVariable Long id) {
        TituloDto titulo = tituloService.buscarPorId(id);
        return ResponseEntity.ok().body(titulo);
    }
}
