package api.tasks.apresentation.dto;

import api.tasks.domain.model.Status;
import api.tasks.apresentation.views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "status", "createdAt"})
public class TaskDto {
    @Schema(type = "integer", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonView(Views.Public.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(type = "string", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 255, example = "Lavar louça", description = "Título da tarefa")
    @JsonView(Views.Public.class)
    @JsonProperty(required = true)
    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres.")
    private String title;

    @Schema(type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Texto longo que descreve a tarefa, opcional", example = "Deixar as louças lavadas para a festa a noite", nullable = true)
    @JsonView(Views.Public.class)
    private String description;

    @Schema(type = "string", example = "PENDING", description = "Status atual da tarefa (ex: PENDING, IN_PROGRESS, DONE)")
    @JsonView(Views.Public.class)
    private Status status;

    @Schema(type = "string", format = "date-time", accessMode = Schema.AccessMode.READ_ONLY, example = "2021-09-20T12:00:00Z", description = "Visto apenas pelos admin do sistema, não deve ser atualizável. Indica o timestamp da criação do objeto")
    @JsonView(Views.Admin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
}
