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
@ToString(of = {"id", "title", "status"})
@EqualsAndHashCode(of = {"id"})
public class Task {

    @GeneratedValue
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void completarTarefa() {
        switch (this.status) {
            case PENDENTE:
                this.status = Status.FINALIZADO;
                break;
            case CANCELADO:
                throw new IllegalOperationFromState("Não é possivel completar tarefas canceladas");
            case FINALIZADO:
                break;
        }
    }

    public void cancelarTarefa() {
        switch (this.status) {
            case FINALIZADO:
            case PENDENTE:
                this.status = Status.CANCELADO;
                break;
            case CANCELADO:
                break;
        }
    }

    public void reativarTarefa() {
        switch (this.status) {
            case CANCELADO:
                this.status = Status.PENDENTE;
                break;
            case FINALIZADO:
            case PENDENTE:
                break;
        }
    }

    @PrePersist
    public void prePersist() {
        this.status = Status.PENDENTE;
        this.createdAt = LocalDateTime.now();
    }
}
