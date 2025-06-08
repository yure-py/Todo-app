package org.example.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.service.TaskDTO;
import org.example.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/task")
@Validated
@Tag(name = "Tasks", description = "Api para gerenciamento de Tarefas")
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;

    public TaskControllerImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO task, UriComponentsBuilder ucb) {
        Long id = taskService.save(task);
        URI uri = ucb.path("/task/{id}")
                     .buildAndExpand(id)
                     .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping
    public Page<TaskDTO> listAll(
            @PositiveOrZero @RequestParam(defaultValue = "0") int page,
            @Positive @Max(100) @RequestParam(defaultValue = "10", required = false) int size) {
        return taskService.getPaginatedTasks(page, size);
    }

    @PutMapping("/complete")
    public TaskDTO complete(@RequestParam Long id) {
        return taskService.complete(id);
    }

    @PutMapping("/cancel")
    public TaskDTO cancel(@RequestParam Long id) {
        return taskService.cancel(id);
    }

    @PutMapping("/reopen")
    public TaskDTO reopen(@RequestParam Long id) {
        return taskService.reopen(id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        taskService.delete(id);
    }
}
