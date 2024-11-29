import java.util.Date;
import java.util.LinkedHashMap;

public class Student extends User {
    private String studentID;
    private School school;
    private STUDENTDEGREE degree;
    private double gpa;
    private int year_of_study;

}

enum STUDENTDEGREE {
    BACHELOR,
    MASTER,
    DOCTORAL
}