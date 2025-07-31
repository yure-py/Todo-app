package api.tasks.infra.exceptions.exceptionClasses;

public class IllegalOperationFromState extends RuntimeException {
    public IllegalOperationFromState(String message) {
        super(message);
    }
}
