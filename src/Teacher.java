import java.util.Date;

public class Teacher extends Employee {
    private Course course;
    private TEACHERDEGREE degree;
}

enum TEACHERDEGREE {
    PROFESSOR,
    ASSOCIATE_PROFESSOR,
    ASSISTANT_PROFESSOR,
    SENIOR_LECTURER,
    LECTURER,
    TUTOR
}