package api.tasks.data;


import api.tasks.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingIgnoreCase(String titulo);
}
