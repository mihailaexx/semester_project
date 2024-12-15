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
    private Map<Course, Mark> marks; // Keep track of marks separately
    private double gpa;
    private STUDENTDEGREE degree;
    private STUDENTTYPE type;

    private static final int MAX_CREDITS = 21;

    public Student(String name, String surname, SEX sex, Date birthDate, String phoneNumber,
                   String citizenship, String password, String email, String major,
                   int yearOfStudy, STUDENTDEGREE degree, STUDENTTYPE type) {
        super(name, surname, sex, birthDate, email, email, password, phoneNumber, citizenship);
        this.studentId = generateStudentID(); // Use a method to generate unique IDs
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.type = type;
        this.degree = degree;
        this.enrolledCourses = new ArrayList<>();
        this.marks = new HashMap<>();
        this.gpa = 0.0;
    }

    // Getters
    public String getStudentID() { return studentId; }
    public String getMajor() { return major; }
    public int getYearOfStudy() { return yearOfStudy; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public double getGpa() { return gpa; }
    public STUDENTDEGREE getDegree() { return degree; }
    public STUDENTTYPE getType() { return type; }

    // Setters
    public void setMajor(String major) { this.major = major; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy;}

    // Other methods

    private String generateStudentID() {
        // in future; yearOfstudy % 100 + {'B','M','P'} + {'01','02',....'18} + 4 random digits
        return "STU" + System.currentTimeMillis();
    }

    public int getTotalCurrentCredits() {
        return enrolledCourses.stream().mapToInt(Course::getCredits).sum();
    }

    public void registerForCourse(Course course) throws CourseRegistrationException {
        if (getTotalCurrentCredits() + course.getCredits() > MAX_CREDITS) {
            throw new CourseRegistrationException("Cannot register for " + course.getName() + ": exceeding max credits.");
        }
        enrolledCourses.add(course);
    }

    public void viewTranscript() {
        System.out.println("Transcript for: " + getName() + " " + getSurname() + " (ID: " + studentId + ")");
        for (Course course : enrolledCourses) {
            Mark mark = marks.get(course);
            if (mark != null) {
                double finalGrade = calculateFinalGrade(mark);
                double gradePoint = gradeToGpa(finalGrade);

                System.out.printf("Course: %s (Credits: %d) Final Grade: %.2f (GPA: %.2f)%n",
                        course.getName(), course.getCredits(), finalGrade, gradePoint);
            } else {
                System.out.println("Course: " + course.getName() + " (No marks yet)");
            }
        }
        calculateGpa();
        System.out.printf("Overall GPA: %.2f%n", gpa);
    }

    public void viewMarks() {
        for (Course course : enrolledCourses) {
            Mark mark = marks.get(course);
            if (mark != null) {
                System.out.println(course.getName() + ": " + mark);
            } else {
                System.out.println(course.getName() + ": No marks yet.");
            }
        }
    }

    public void addMark(Course course, Mark mark) {
        marks.put(course, mark);
        calculateGpa();
    }

    private double calculateFinalGrade(Mark m) {
        double att1 = (m.getAtt1() != null) ? m.getAtt1() : 0;
        double att2 = (m.getAtt2() != null) ? m.getAtt2() : 0;
        double finalExam = (m.getFinalExam() != null) ? m.getFinalExam() : 0;

        return att1 * 0.3 + att2 * 0.3 + finalExam * 0.4;
    }

    private double gradeToGpa(double finalGrade) {
        if (finalGrade >= 95) return 4.0;
        else if (finalGrade >= 90) return 3.67;
        else if (finalGrade >= 85) return 3.33;
        else if (finalGrade >= 80) return 3.0;
        else if (finalGrade >= 75) return 2.67;
        else if (finalGrade >= 70) return 2.33;
        else if (finalGrade >= 65) return 2.0;
        else if (finalGrade >= 60) return 1.67;
        else if (finalGrade >= 55) return 1.33;
        else if (finalGrade >= 50) return 1.0;
        else return 0.0;
    }

    private void calculateGpa() {
        double totalWeightedGrade = 0;
        int totalCredits = 0;

        for (Course course : enrolledCourses) {
            Mark mark = marks.get(course);
            if (mark != null) {
                double finalGrade = calculateFinalGrade(mark);
                double gradePoint = gradeToGpa(finalGrade);

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
                "studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", enrolledCourses=" + enrolledCourses +
                ", gpa=" + gpa +
                ", degree=" + degree +
                ", type=" + type +
                '}';
    }


    public List<Course> getCourses() {
        return enrolledCourses;
    }
}