package org.example.service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.infrastructure.exceptionHandlers.InvalidTaskStateTransitionException;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String title;
    @Column(nullable = false, unique = true, length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public TaskEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void complete() {
        if (this.status == Status.IN_PROGRESS) {
            this.status = Status.COMPLETED;
            return;
        }
        if (this.status == Status.COMPLETED)
            throw new InvalidTaskStateTransitionException("Task already completed!");
        throw new InvalidTaskStateTransitionException("Canceled Tasks can not be completed");
    }

    public void deComplete() {
        if (this.status == Status.COMPLETED) {
            this.status = Status.IN_PROGRESS;
            return;
        }
        if (this.status == Status.IN_PROGRESS)
            throw new InvalidTaskStateTransitionException("TaskEntity already In Progress!");
        throw new InvalidTaskStateTransitionException("Canceled Tasks can not be deCompleted");
    }

    public void cancel() {
        if (this.status == Status.IN_PROGRESS) {
            this.status = Status.CANCELED;
            return;
        }
        if (this.status == Status.CANCELED)
            throw new InvalidTaskStateTransitionException("TaskEntity already canceled!");
        throw new InvalidTaskStateTransitionException("Completed Tasks can not be canceled");
    }

    @PrePersist
    public void ensureDefaultStatus() {
        this.status = Status.IN_PROGRESS;
    }

    @Override
    public String toString() {
        return "TaskEntity{id=%d, title='%s', description='%s', status=%s}".formatted(id, title, description, status);
    }
}
