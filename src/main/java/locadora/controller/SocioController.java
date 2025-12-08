package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.Socio;
import locadora.domain.dto.socio.SocioRequestDto;
import locadora.domain.dto.socio.SocioResponseDto;
import locadora.mapper.SocioMapper;
import locadora.service.SocioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socios")
@AllArgsConstructor
@Tag(
        name = "Socios",
        description = "Endpoints para gerenciamento de Sócios"
)
public class SocioController {

    private final SocioService service;
    private final SocioMapper mapper;

    @GetMapping("/listarSocios")
    public ResponseEntity<?> listarSocios(){
        return ResponseEntity.ok().body(service.listar());
    }

    @PostMapping("/salvarSocio")
    public ResponseEntity<?> salvarSocio(@RequestBody @Valid SocioRequestDto Socio){
        service.salvar(Socio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarSocio")
    public ResponseEntity<?> atualizarSocio(@PathVariable Long id,@RequestBody @Valid SocioRequestDto Socio){
        service.atualizar(id,Socio);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/inativarSocio/{id}")
    public ResponseEntity<?> inativarSocio(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reativarSocio/{id}")
    public ResponseEntity<?> reativarSocio(@PathVariable Long id){
        service.reativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarSocio/{id}")
    public ResponseEntity<SocioResponseDto> buscarSocioPorId(@PathVariable Long id) {
        Socio Socio = service.buscarPorId(id); // Chama o serviço para buscar pelo ID
        return ResponseEntity.ok().body(mapper.toDto(Socio));
    }

    @DeleteMapping("/deletarSocio/{id}")
    public ResponseEntity<?> deletarSocio(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
