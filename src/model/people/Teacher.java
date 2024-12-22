package model.people;

import enums.SEX;
import enums.TEACHERDEGREE;
import model.academic.Course;
import model.research.Researcher;

import java.io.Serializable;
import java.util.*;

public class Teacher extends Employee implements Serializable {
    private static final long serialVersionUID = 4L;

    private String department;
    private TEACHERDEGREE teacherDegree; //  Professor, Assistant Professor, etc.
    private List<Course> courses;
    private Map<Student, Integer> ratings;

    public Teacher(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary, String department, TEACHERDEGREE teacherDegree) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
        if (teacherDegree == TEACHERDEGREE.PROFESSOR) {
            this.setResearcher(new Researcher(this));
        }
        this.department = department;
        this.teacherDegree = teacherDegree;
        this.courses = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    // Getters
    public String getDepartment() { return department; }
    public TEACHERDEGREE getTeacherDegree() { return teacherDegree; }
    public List<Course> getCourses() { return courses; }

    // Setters
    public void setDepartment(String department) { this.department = department; }
    public void setTeacherDegree(TEACHERDEGREE title) {
        this.teacherDegree = teacherDegree;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void addRating(Student student, int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.put(student, rating);
        } else {
            System.err.println("Invalid rating. Rating should be between 1 and 5.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        return ratings.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(department, teacher.department) &&
                teacherDegree == teacher.teacherDegree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, teacherDegree);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + getName() + '\'' +
                "surname='" + getSurname() + '\'' +
                "department='" + department + '\'' +
                ", teacherDegree=" + teacherDegree +
                ", courses=" + courses +
                ", ratings=" + ratings +
                "} " + super.toString();
    }
}