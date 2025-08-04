package api.tasks.domain.mapper;

import api.tasks.apresentation.dto.TaskDto;
import api.tasks.domain.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toModel(TaskDto dto);
}
