package api.tasks.domain.service;

import api.tasks.domain.mapper.TaskMapper;
import api.tasks.repository.TaskRepository;
import api.tasks.apresentation.dto.TaskDto;
import api.tasks.domain.model.Status;
import api.tasks.domain.model.Task;
import api.tasks.infra.exceptions.exceptionClasses.RecursoNaoEncontrado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;


    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskDto buscarTarefaPorId(Long id) {
        Task entity = findOrElseThrow(id);
        return null;
    }

    public TaskDto cadastrarTarefa(TaskDto dto) {
        var entity = mapper.toModel(dto);
        log.trace("Entidade mapeada: {}", entity);

        var savedEntity = repository.save(entity);
        log.info("Pessoa criada com sucesso. ID: {}", savedEntity.getId());

        return mapper.toDto(savedEntity);
    }

    public void removerTarefa(Long id) {
    }

    public TaskDto atualizarTarefa(Long id, TaskDto task) {
        return null;
    }

    public Page<TaskDto> buscasTodasAsTarefas(Pageable pageable) {
        return null;
    }


    // Helpers

    private Task findOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RecursoNaoEncontrado("O id " + id + " Não existe no sistema!")
        );
    }


    // TODO

    public List<TaskDto> buscasTarefasPorStatus(Status status) {
        return null;
    }

    public List<TaskDto> buscasTarefasPorTitulo(String titulo) {
        return null;
    }

    public void completarTarefa(TaskDto task) {
    }

    public void cancelarTarefa(TaskDto task) {
    }

    public void reativarTarefa(TaskDto task) {
    }
}
