package org.example.infrastructure.exceptionHandlers;

public class InvalidTaskStateTransitionException  extends RuntimeException {
    public InvalidTaskStateTransitionException (String s) {
        super(s);
    }
}
