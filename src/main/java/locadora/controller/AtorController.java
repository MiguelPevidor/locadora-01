package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.Ator;
import locadora.domain.dto.ator.AtorRequestDto;
import locadora.domain.dto.ator.AtorResponseDto;
import locadora.mapper.AtorMapper;
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
    private final AtorMapper atorMapper;

    @GetMapping("/listarAtores")
    public ResponseEntity<?> listarAtores(){
       return ResponseEntity.ok().body(atorService.listar());
    }

    @PostMapping("/salvarAtor")
    public ResponseEntity<?> salvarAtor(@RequestBody @Valid AtorRequestDto ator){
        atorService.salvar(ator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarAtor")
    public ResponseEntity<?> atualizarAtor(@PathVariable Long id,@RequestBody @Valid AtorRequestDto ator){
        atorService.atualizar(id,ator);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletarAtor/{id}")
    public ResponseEntity<?> deletarAtor(@PathVariable Long id){
        atorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarAtor/{id}")
    public ResponseEntity<AtorResponseDto> buscarAtorPorId(@PathVariable Long id) {
        Ator ator = atorService.buscarPorId(id); // Chama o serviço para buscar pelo ID
        return ResponseEntity.ok().body(atorMapper.toDto(ator));
    }


}
