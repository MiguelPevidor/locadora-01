package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.dto.classe.ClasseRequestDto;
import locadora.domain.dto.classe.ClasseResponseDto;
import locadora.mapper.ClasseMapper;
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
    private final ClasseMapper classeMapper;

    @GetMapping("/listarClasses")
    public ResponseEntity<?> listarClasses(){
        return ResponseEntity.ok().body(classeService.listar());
    }

    @PostMapping("/salvarClasse")
    public ResponseEntity<?> salvarClasse(@RequestBody @Valid ClasseRequestDto classe){
        classeService.salvar(classe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}/editarClasse")
    public ResponseEntity<?> atualizarClasse(@PathVariable Long id,@RequestBody @Valid ClasseRequestDto classe){
        classeService.atualizar(id,classe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletarClasse/{id}")
    public ResponseEntity<?> deletarClasse(@PathVariable Long id){
        classeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarclasse/{id}")
    public ResponseEntity<ClasseResponseDto> buscarAtorPorId(@PathVariable Long id) {
        ClasseResponseDto classe = classeMapper.toDto(classeService.buscarPorId(id));
        return ResponseEntity.ok().body(classe);
    }


}
