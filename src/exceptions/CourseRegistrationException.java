package exceptions;

public class CourseRegistrationException extends RuntimeException {
  public CourseRegistrationException(String message) {
    super(message);
  }
}
