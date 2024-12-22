package exceptions;

import java.io.Serializable;

public class InvalidMarkException extends Exception implements Serializable {
    private static final long serialVersionUID = 22L;
    public InvalidMarkException(String message) {
        super(message);
    }
    public InvalidMarkException(String message, Throwable cause) {
        super(message, cause);
    }
}
