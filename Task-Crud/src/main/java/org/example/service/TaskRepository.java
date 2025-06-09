package org.example.service;

import org.example.service.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// define o contrato para comunicação entre DATA-SERVICE
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    boolean existsByTitle(String title);
}
