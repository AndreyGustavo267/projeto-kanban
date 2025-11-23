package br.edu.ifsp.kanban.controller.resource;

import br.edu.ifsp.kanban.controller.dto.factoryDto.TarefaDtoFactory;
import br.edu.ifsp.kanban.controller.dto.request.TarefaRequestDto;
import br.edu.ifsp.kanban.controller.dto.response.TarefaResponseDto;
import br.edu.ifsp.kanban.model.canonical.TarefaCanonical;
import br.edu.ifsp.kanban.model.canonical.factoryCanonical.TarefaCanonicalFactory;
import br.edu.ifsp.kanban.model.entity.Tarefa;
import br.edu.ifsp.kanban.service.TarefaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TarefaResponseDto> getTarefaPorId(@PathVariable Integer id) {
        TarefaResponseDto tarefa = TarefaDtoFactory.canonicalToResponseDto(service.buscaTarefaPorId(id));
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TarefaResponseDto> postTarefaPorId(TarefaRequestDto dto) {
        service.criarTarefa(TarefaDtoFactory.dtoToCanonical(dto));
        return ResponseEntity.ok().build();

    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TarefaResponseDto> putTarefaPorId(@PathVariable Integer id, TarefaRequestDto dto) {
        TarefaCanonical canonical = TarefaDtoFactory.dtoToCanonical(dto);
        canonical.setIdTarefa(id);
        service.atualizarTarefa(canonical);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<TarefaResponseDto> postTarefaPorId(@PathVariable Integer id) {
        service.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
