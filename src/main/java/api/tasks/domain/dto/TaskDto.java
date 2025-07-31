package api.tasks.domain.dto;

import api.tasks.domain.model.Status;
import api.tasks.web.Jackson.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "completed", "createdAt"})
public class TaskDto {
    @JsonView(Views.Public.class)
    private Long id;
    @JsonView(Views.Public.class)
    private String title;
    @JsonView(Views.Public.class)
    private String description;
    @JsonView(Views.Public.class)
    private Status completed;
    @JsonView(Views.Admin.class)
    private LocalDateTime createdAt;
}
