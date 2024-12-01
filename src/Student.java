import java.util.Date;
import java.util.Stack;

public class Student extends User {
    private School school;

    private String studentID;
    private STUDENTDEGREE degree;
    private double gpa;
    private int year_of_study;
    private Stack<Course> courses;

    public Student(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, String studentID, School school, STUDENTDEGREE degree, int year_of_study) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.studentID = studentID;
        this.school = school; school.addStudent(this);
        this.degree = degree;
        this.year_of_study = year_of_study;
        super.email = name.charAt(0) + "_" + surname + "@kbtu.kz";
    }

    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}

enum STUDENTDEGREE {
    BACHELOR,
    MASTER,
    DOCTORAL
}