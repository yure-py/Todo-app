package org.example.service;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.service.domain.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
