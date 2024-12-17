package exceptions;

public class InvalidMarkException extends Exception {
    public InvalidMarkException(String message) {
        super(message);
    }
    public InvalidMarkException(String message, Throwable cause) {
        super(message, cause);
    }
}
