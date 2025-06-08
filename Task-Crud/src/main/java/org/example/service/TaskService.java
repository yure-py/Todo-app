package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.service.domain.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    // Business Logic

    public Long save(TaskDTO task) {
        TaskEntity entity = taskMapper.toEntity(task);
        taskRepository.save(entity);
        return entity.getId();
    }

    public TaskDTO findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("TaskEntity not found with id: " + id));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO complete(Long id) {
        TaskEntity taskEntity = findByIdOrThrow(id);
        taskEntity.complete();
        return merge(taskEntity);
    }

    public TaskDTO cancel(Long id) {
        TaskEntity taskEntity = findByIdOrThrow(id);
        taskEntity.cancel();
        return merge(taskEntity);
    }

    public TaskDTO reopen(Long id) {
        TaskEntity taskEntity = findByIdOrThrow(id);
        taskEntity.deComplete();
        return merge(taskEntity);
    }

    public Page<TaskDTO> getPaginatedTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable)
                             .map(taskMapper::toDTO);
    }


    // helpers

    private TaskDTO merge(TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
        return taskMapper.toDTO(taskEntity);
    }

    private TaskEntity findByIdOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskEntity not found with id: " + id));
    }
}
