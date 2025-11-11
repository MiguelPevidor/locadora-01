package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.dto.titulo.TituloRequestDto;
import locadora.domain.dto.titulo.TituloResponseDto;
import locadora.mapper.TituloMapper;
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
    private final TituloMapper tituloMapper;


    @GetMapping("/listarTitulos")
    public ResponseEntity<?> listarTitulos(){

        return ResponseEntity.ok().body(tituloService.listar());
    }

    @PostMapping("/salvarTitulo")
    public ResponseEntity<?> salvarTitulo(@RequestBody @Valid TituloRequestDto titulo){
        tituloService.salvar(titulo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}/editarTitulo")
    public ResponseEntity<?> atualizarTitulo(@PathVariable Long id,@RequestBody @Valid TituloRequestDto titulo){
        tituloService.atualizar(id,titulo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarTitulo/{id}")
    public ResponseEntity<?> deletarTitulo(@PathVariable Long id){
        tituloService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarTitulo/{id}")
    public ResponseEntity<TituloResponseDto> buscarTituloPorId(@PathVariable Long id) {
        TituloResponseDto titulo = tituloMapper.toDto(tituloService.buscarPorId(id));
        return ResponseEntity.ok().body(titulo);
    }
}
