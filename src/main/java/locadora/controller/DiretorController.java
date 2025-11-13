package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.dto.diretor.DiretorRequestDto;
import locadora.domain.dto.diretor.DiretorResponseDto;
import locadora.mapper.DiretorMapper;
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
    private final DiretorMapper diretorMapper;

    @GetMapping("/listarDiretores")
    public ResponseEntity<?> listarDiretores(){
        return ResponseEntity.ok().body(diretorService.listar());
    }

    @PostMapping("/salvarDiretor")
    public ResponseEntity<?> salvarDiretor(@RequestBody @Valid DiretorRequestDto diretor){
        diretorService.salvar(diretor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarDiretor")
    public ResponseEntity<?> atualizarDiretor(@PathVariable Long id,@RequestBody @Valid DiretorRequestDto diretor){
        diretorService.atualizar(id, diretor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarDiretor/{id}")
    public ResponseEntity<?> deletarDiretor(@PathVariable Long id){
        diretorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarDiretor/{id}")
    public ResponseEntity<DiretorResponseDto> buscarAtorPorId(@PathVariable Long id) {
        DiretorResponseDto diretor = diretorMapper.toDto(diretorService.buscarPorId(id));
        return ResponseEntity.ok().body(diretor);
    }


}
