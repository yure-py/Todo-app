package api.tasks.domain.model;

import api.tasks.infra.exceptions.exceptionClasses.IllegalOperationFromState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(of = {"id", "title", "completed"})
@EqualsAndHashCode(of = {"id"})
public class Task {

    @GeneratedValue
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status completed;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void completarTarefa() {
        switch (this.completed) {
            case PENDENTE:
                this.completed = Status.FINALIZADO;
                break;
            case CANCELADO:
                throw new IllegalOperationFromState("Não é possivel completar tarefas canceladas");
            case FINALIZADO:
                break;
        }
    }

    public void cancelarTarefa() {
        switch (this.completed) {
            case FINALIZADO:
            case PENDENTE:
                this.completed = Status.CANCELADO;
                break;
            case CANCELADO:
                break;
        }
    }

    public void reativarTarefa() {
        switch (this.completed) {
            case CANCELADO:
                this.completed = Status.PENDENTE;
                break;
            case FINALIZADO:
            case PENDENTE:
                break;
        }
    }

    @PrePersist
    public void prePersist() {
        this.completed = Status.PENDENTE;
        this.createdAt = LocalDateTime.now();
    }
}
