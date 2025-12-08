package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.Dependente;
import locadora.domain.dto.dependente.DependenteRequestDto;
import locadora.domain.dto.dependente.DependenteResponseDto;
import locadora.mapper.DependenteMapper;
import locadora.service.DependenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dependentes")
@AllArgsConstructor
@Tag(
        name = "Dependentes",
        description = "Endpoints para gerenciamento de Dependentes"
)
public class DependenteController {
    private final DependenteService service;
    private final DependenteMapper mapper;

    @GetMapping("/listarDependentes")
    public ResponseEntity<?> listarDependentes(){
        return ResponseEntity.ok().body(service.listar());
    }

    @PostMapping("/incluirDependente/{idSocio}")
    public ResponseEntity<?> salvarDependente(@PathVariable Long idSocio, @RequestBody @Valid DependenteRequestDto dependente){
        service.salvar(idSocio, dependente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarDependente")
    public ResponseEntity<?> atualizarDependente(@PathVariable Long id,@RequestBody @Valid DependenteRequestDto dependente){
        service.atualizar(id,dependente);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/inativarDependente/{id}")
    public ResponseEntity<?> inativarDependente(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reativarDependente/{id}")
    public ResponseEntity<?> reativarDependente(@PathVariable Long id){
        service.reativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletarDependente/{id}")
    public ResponseEntity<?> deletarDependente(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/buscarDependente/{id}")
    public ResponseEntity<DependenteResponseDto> buscarDependentePorId(@PathVariable Long id) {
        Dependente dependente = service.buscarPorId(id); // Chama o serviço para buscar pelo ID
        return ResponseEntity.ok().body(mapper.toDto(dependente));
    }
}
