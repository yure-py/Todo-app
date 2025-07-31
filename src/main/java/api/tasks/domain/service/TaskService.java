package api.tasks.domain.service;

import api.tasks.data.TaskRepository;
import api.tasks.domain.model.Status;
import api.tasks.domain.model.Task;
import api.tasks.infra.exceptions.exceptionClasses.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task buscarTarefaPorId(Long id) {
        Task entity = findOrElseThrow(id);
        return null;
    }

    private Task findOrElseThrow(Long id) {
        return taskRepository.findById(id).orElseThrow(
            () -> new RecursoNaoEncontrado("O id " + id + " Não existe no sistema!")
        );
    }

    public Task cadastrarTarefa(Task task) {
        return null;
    }

    public Task removerTarefa(Long id) {
        return null;
    }

    public Task atualizarTarefa(Long id, Task task) {
        return null;
    }

    public List<Task> buscasTodasAsTarefas() {
        return null;
    }

    public List<Task> buscasTarefasPorStatus(Status status) {
        return null;
    }

    public List<Task> buscasTarefasPorTitulo(String titulo) {
        return null;
    }

    public void completarTarefa(Task task) {
        task.completarTarefa();
    }

    public void cancelarTarefa(Task task) {
        task.cancelarTarefa();
    }

    public void reativarTarefa(Task task) {
        task.reativarTarefa();
    }
}
