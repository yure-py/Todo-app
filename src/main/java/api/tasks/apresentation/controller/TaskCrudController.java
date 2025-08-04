package api.tasks.apresentation.controller;

import api.tasks.apresentation.controller.spec.TaskApi;
import api.tasks.apresentation.dto.TaskDto;
import api.tasks.apresentation.views.Views;
import api.tasks.domain.service.TaskService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/task")
@Validated
public class TaskCrudController implements TaskApi {

    private final TaskService service;

    public TaskCrudController(TaskService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        TaskDto dto = service.buscarTarefaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<TaskDto> page = service.buscasTodasAsTarefas(pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<?> create(TaskDto dto, UriComponentsBuilder ucb) {
        TaskDto entity = service.cadastrarTarefa(dto);
        URI uri = ucb.path("api/v1/task/{id}")
                     .buildAndExpand(entity.getId())
                     .toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    public ResponseEntity<?> delete(Long id) {
        service.removerTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> update(Long id, TaskDto dto) {
        TaskDto entity = service.atualizarTarefa(id, dto);
        return ResponseEntity.ok(entity);
    }
}
