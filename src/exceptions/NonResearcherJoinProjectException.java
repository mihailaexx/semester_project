package exceptions;

import java.io.Serializable;

public class NonResearcherJoinProjectException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 15L;

    public NonResearcherJoinProjectException(String message) {
        super(message);
    }
}
