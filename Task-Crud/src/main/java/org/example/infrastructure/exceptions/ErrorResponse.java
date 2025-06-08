package org.example.infrastructure.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String path,
        LocalDateTime timestamp,
        List<String> messages
) {}