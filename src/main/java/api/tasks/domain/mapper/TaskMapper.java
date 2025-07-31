package api.tasks.domain.mapper;

import api.tasks.domain.dto.TaskDto;
import api.tasks.domain.model.Task;

public class TaskMapper {
    public TaskDto toDto(Task task) {
        var entity = task;
        return new TaskDto(entity.getId(),
                           entity.getTitle(),
                           entity.getDescription(),
                           entity.getCompleted(),
                           entity.getCreatedAt());

    }
}
