package org.example.service;

import org.example.service.domain.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(TaskEntity entity);
    TaskEntity toEntity(TaskDTO dto);
}