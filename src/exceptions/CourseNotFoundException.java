package exceptions;

import java.io.Serializable;

public class CourseNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 16L;
    public CourseNotFoundException(String message) {
        super(message);
    }
}
