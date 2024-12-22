package exceptions;

import java.io.Serializable;

public class AuthenticationException extends Exception implements Serializable {
    private static final long serialVersionUID = 13L;
    public AuthenticationException(String message) {
        super(message);
    }
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}