package org.example.infrastructure.exceptionHandlers;

public class DuplicatedEntryException extends RuntimeException {
    public DuplicatedEntryException(String message) {
        super(message);
    }
}
