package exception;

public class EmptyMovesException extends RuntimeException {
    public EmptyMovesException(String message) {
        super(message);
    }
}
