import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Stack;

public class Student extends User {
    private String studentID;
    private School school;
    private STUDENTDEGREE degree;
    private double gpa;
    private int year_of_study;
    private Stack<Course> courses;
}

enum STUDENTDEGREE {
    BACHELOR,
    MASTER,
    DOCTORAL
}