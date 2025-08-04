package api.tasks.apresentation.controller.spec;

import api.tasks.apresentation.dto.TaskDto;
import api.tasks.apresentation.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Tasks Crud Controller", description = "Endpoints for Managing Tasks")
@RequestMapping("/api/v1/task")
public interface TaskApi {
    @Operation(summary = "Find a task by ID", description = "Returns a single task based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content)
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> findById(@Parameter(description = "ID of the task to be searched")
                               @PathVariable
                               @Positive
                               Long id);


    @Operation(summary = "List all tasks", description = "Returns a paginated list of all tasks.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> findAll(@ParameterObject
                              @PageableDefault(size = 10, sort = "id")
                              Pageable pageable);


    @Operation(summary = "Create a new task", description = "Creates a new task and returns the created task with its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> create(@RequestBody
                             @Valid TaskDto dto,
                             @Parameter(hidden = true)
                             UriComponentsBuilder ucb);


    @Operation(summary = "Delete a task", description = "Deletes a task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found with the given ID", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    @JsonView(Views.Public.class)
    ResponseEntity<?> delete(@PathVariable Long id);


    @Operation(summary = "Update an existing task", description = "Updates the information of an existing task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    ResponseEntity<?> update(@Parameter(description = "ID of the task to be updated")
                             @PathVariable Long id,
                             @Parameter(description = "Task object with updated data")
                             @RequestBody
                             @Valid
                             TaskDto dto);
}
