package exceptions;

import java.io.Serializable;

public class CourseRegistrationException extends Exception implements Serializable {
    private static final long serialVersionUID = 14L;

    public CourseRegistrationException(String message) {
        super(message);
    }
}
