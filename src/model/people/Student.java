package model.people;

import enums.SEX;
import enums.STUDENTDEGREE;
import enums.STUDENTTYPE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.academic.Mark;

import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 5L;

    private String studentId;
    private String major;
    private int yearOfStudy;
    private List<Course> enrolledCourses;
    private Map<Course, Mark> marks;
    private double gpa;
    private STUDENTDEGREE degree;
    private STUDENTTYPE type;

    private static final Map<String, String> DEPARTMENT_CODES = new HashMap<>();
    private static final int MAX_CREDITS = 21;

    public Student(String name, String surname, SEX sex, Date birthDate, String phoneNumber,
                   String citizenship, String password, String email, String major,
                   int yearOfStudy, STUDENTDEGREE degree, STUDENTTYPE type) {
        super(name, surname, sex, birthDate, email, email, password, phoneNumber, citizenship);
        this.studentId = generateStudentID();
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.type = type;
        this.degree = degree;
        this.enrolledCourses = new ArrayList<>();
        this.marks = new HashMap<>();
        this.gpa = 0.0;
    }

    static {
        DEPARTMENT_CODES.put("SITE", "03");
        DEPARTMENT_CODES.put("SEOG", "05");
        DEPARTMENT_CODES.put("BS", "01");
        DEPARTMENT_CODES.put("KMA", "08");
        DEPARTMENT_CODES.put("IS", "18");
    }
    // Getters
    public String getStudentID() { return studentId; }
    public String getMajor() { return major; }
    public int getYearOfStudy() { return yearOfStudy; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public double getGpa() {
        calculateGpa();
        return gpa; }
    public STUDENTDEGREE getDegree() { return degree; }
    public STUDENTTYPE getType() { return type; }
    public Map<Course, Mark> getMarks() { return marks; }
    // Setters
    public void setMajor(String major) { this.major = major; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy;}
    public void setType(STUDENTTYPE studenttype) {this.type = studenttype;}

    private String generateStudentID() {
        String enrollmentYear = String.valueOf(2024 - yearOfStudy).substring(2);
        String programCode = "B";
        String departmentCode = DEPARTMENT_CODES.getOrDefault(major, "00");
        String uniqueDigits = String.format("%04d", new Random().nextInt(1000));

        return enrollmentYear + programCode + departmentCode + uniqueDigits;
    }

    public int getTotalCurrentCredits() {
        return enrolledCourses.stream().mapToInt(Course::getCredits).sum();
    }

    public void registerForCourse(Course course) throws CourseRegistrationException {
        if (getTotalCurrentCredits() + course.getCredits() > MAX_CREDITS) {
            throw new CourseRegistrationException("Cannot register for " + course.getName() + ": exceeding max credits.");
        }

        if (enrolledCourses.contains(course)) {
            throw new CourseRegistrationException("Already registered for course: " + course.getCode());
        }
        enrolledCourses.add(course);
    }

    public void addMark(Course course, Mark mark) {
        marks.put(course, mark);
        calculateGpa();
    }

    public void calculateGpa() {
        double totalWeightedGrade = 0;
        int totalCredits = 0;

        for (Course course : enrolledCourses) {
            Mark mark = marks.get(course);
            if (mark != null) {
                double gradePoint = mark.gradeToGpa();
                totalWeightedGrade += gradePoint * course.getCredits();
                totalCredits += course.getCredits();
            }
        }

        this.gpa = (totalCredits > 0) ? totalWeightedGrade / totalCredits : 0;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                "surname='" + getSurname() + '\'' +
                "studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", enrolledCourses=" + enrolledCourses +
                ", gpa=" + gpa +
                ", degree=" + degree +
                ", type=" + type +
                '}';
    }

}