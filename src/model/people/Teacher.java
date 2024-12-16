package model.people;

import enums.SEX;
import enums.TEACHERDEGREE;
import model.academic.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Teacher extends Employee implements Serializable {
    private static final long serialVersionUID = 4L;

    private String department; // Replaces School, as a Teacher belongs to a department
    private TEACHERDEGREE teacherDegree; // e.g., Professor, Assistant Professor, etc.
    private List<Course> courses;
    private List<Integer> ratings;

    public Teacher(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary, String department, TEACHERDEGREE teacherDegree) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
        this.department = department;
        this.teacherDegree = teacherDegree;
        this.courses = new ArrayList<>();
        this.ratings = new ArrayList<>();
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

    // Other methods

    public void addCourse(Course course) {
        courses.add(course);
    }
    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
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