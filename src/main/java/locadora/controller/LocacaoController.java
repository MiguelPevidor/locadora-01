package locadora.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.domain.Locacao;
import locadora.domain.dto.ator.AtorRequestDto;
import locadora.domain.dto.locacao.LocacaoRequestDto;
import locadora.domain.dto.locacao.LocacaoResponseDto;
import locadora.mapper.LocacaoMapper;
import locadora.repository.LocacaoRepository;
import locadora.service.LocacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacoes")
@AllArgsConstructor
@Tag(
        name = "Locação",
        description = "Endpoints para gerenciamento das Locações"
)
public class LocacaoController {

    private final LocacaoService service;
    private final LocacaoMapper mapper;

    @GetMapping("listarLocacoes")
    public ResponseEntity<List<LocacaoResponseDto>> listar(){
        List<Locacao> locacoes = service.listar();
        return ResponseEntity.ok().body(mapper.toDtoList(locacoes));
    }

    @PostMapping("/salvarLocacao")
    public ResponseEntity<LocacaoResponseDto> salvar(@RequestBody LocacaoRequestDto dto){
        service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/editarLocacao")
    public ResponseEntity<?> atualizarlocacao(@PathVariable Long id, @RequestBody @Valid LocacaoRequestDto dto){
        service.atualizar(id,dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{numSerie}/devolver")
    public ResponseEntity<LocacaoResponseDto> atualizarlocacao(@PathVariable String numSerie){
        Locacao locacao = service.efetuarDevolucao(numSerie);
        return ResponseEntity.ok().body(mapper.toDto(locacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLocacao(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscarLocacao/{id}")
    public ResponseEntity<LocacaoResponseDto> buscarPorId(@PathVariable Long id) {
        Locacao locacao = service.buscarPorId(id);
        return ResponseEntity.ok(mapper.toDto(locacao));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<LocacaoResponseDto>> listarPendentes(){
        return ResponseEntity.ok().body(service.listarPendentes());
    }


}
