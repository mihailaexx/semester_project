package model.people;

import enums.SEX;
import enums.TEACHERDEGREE;
import model.misc.School;
import model.misc.University;
import model.academic.Course;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Teacher extends Employee implements Comparable<Person> {
    private School school;

    private Vector<Course> courses;
    private TEACHERDEGREE degree;
    private Vector<Integer> ratings;

    public void sync(University university) {
        university.addEmployee(this);
    }

    public Teacher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, School school, TEACHERDEGREE degree) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.school = school; school.addEmployee(this); // school.getUniversity().addEmployee(this);
        this.courses = new Vector<Course>();
        this.degree = degree;
        this.ratings = new Vector<>();

    }


    public void addCourse(Course course) {
        courses.add(course);
        course.addInstructor(this);
    }

    public void sendComplaint(Dean dean, Vector<Student> students, String complaint, String urgencyLevel) {
        dean.receiveComplaint(this, complaint, urgencyLevel);
    }

    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

//    public void updateDiscipline(Discipline discipline_to_edit, Discipline new_discipline) {
//        // Teacher can't update discipline by itself, instead should be sent message for manager to update
//        // the course accordingly
//        for (Discipline d : disciplines) {
//            if (d.equals(discipline_to_edit)) {
//                d = new_discipline;
//            }
//        }
//    }

    public void updateMark(Student student, Course course, double mark, int i) {
        if (!courses.contains(course)) {
            System.out.println("Teacher is not assigned to this course.");
            return;
        }

        course.updateStudentMark(student, mark, i);
        System.out.println("Mark updated for " + student.getName() + " " + student.getSurname() + " in course " + course.getName());
    }
    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToInt(r->r).average().orElse(0);
    }
    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Teacher teacher)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(school, teacher.school) && Objects.equals(courses, teacher.courses) && degree == teacher.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), school, courses, degree);
    }

    @Override
    public String toString() {
        return "Teacher[[" + super.toString() +
                "], school=" + school +
                ", disciplines=" + courses +
                ", degree=" + degree +
                ']';
    }
}
