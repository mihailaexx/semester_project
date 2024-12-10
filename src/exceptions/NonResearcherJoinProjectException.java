package exceptions;

public class NonResearcherJoinProjectException extends RuntimeException {
    public NonResearcherJoinProjectException(String message) {
        super(message);
    }
}
