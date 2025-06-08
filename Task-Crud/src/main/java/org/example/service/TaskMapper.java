package org.example.service;

import org.example.service.domain.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(TaskEntity entity);
    TaskEntity toEntity(TaskDTO dto);
}