package org.example.service;

import javax.annotation.processing.Generated;
import org.example.service.domain.TaskEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T11:05:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDTO(TaskEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setTitle( entity.getTitle() );
        taskDTO.setDescription( entity.getDescription() );
        taskDTO.setStatus( entity.getStatus() );

        return taskDTO;
    }

    @Override
    public TaskEntity toEntity(TaskDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle( dto.getTitle() );
        taskEntity.setDescription( dto.getDescription() );
        taskEntity.setStatus( dto.getStatus() );

        return taskEntity;
    }
}
