package org.example.infrastructure.exceptionHandlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.example.infrastructure.exceptions.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status, String message, String path, List<String> errors
    ) {
        ErrorResponse error = new ErrorResponse(
                status.value(),
                message,
                path,
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                request.getRequestURI(),
                errors
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request
    ) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Constraint Violation",
                request.getRequestURI(),
                errors
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Resource Not Found",
                request.getRequestURI(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(DuplicatedEntryException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DuplicatedEntryException ex, HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "Data Integrity Violation",
                request.getRequestURI(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(InvalidTaskStateTransitionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTaskStateTransitionException(
            InvalidTaskStateTransitionException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Invalid Task State Transition",
                request.getRequestURI(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnhandledExceptions(
            Exception ex, HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                request.getRequestURI(),
                List.of(ex.getMessage())
        );
    }
}
