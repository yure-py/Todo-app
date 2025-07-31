package api.tasks.infra.exceptions.exceptionClasses;

public class RecursoNaoEncontrado extends RuntimeException {
    public RecursoNaoEncontrado(String message) {
        super(message);
    }
}
