package org.example.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.service.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public interface TaskController {
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully. Returns location header with task URI"),
        @ApiResponse(responseCode = "400", description = "Invalid input - Missing required fields or validation errors"),
        @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    ResponseEntity<?> create(@Valid @RequestBody TaskDTO task, UriComponentsBuilder ucb);
}