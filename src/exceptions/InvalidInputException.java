package exceptions;

import java.io.Serializable;
import java.lang.foreign.SegmentAllocator;

public class InvalidInputException extends Exception implements Serializable {
    private static final long serialVersionUID = 17L;
    public InvalidInputException(String message) {
        super(message);
    }
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}