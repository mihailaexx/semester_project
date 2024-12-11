package model.manager;

import enums.SEX;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.misc.University;
import model.people.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class OrManager extends Employee implements Comparable<Person> {
    public void sync(University university) {
        university.addEmployee(this);
    }

    public OrManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void seeRequests() {
        //
    }

    public void approveRequest(Student student, Course course) {
        try {
            student.registerForCourse(course);
            System.out.println("Request approved for student " + student.getName());
        } catch (Exception e) {
            // For future: add new exceptions why ORmanager can't approve request for course registration
            System.out.println("Could not approve request: " + e.getMessage());
        }
    }

    public void addCourseForRegistration(Course course, String major, int year, University university) {
        // university.addCourseForMajorYear(major, year, course);
        // should be implemented in future
        //
    }

    public void assignCourseToTeacher(Course course, Teacher teacher) {
        teacher.addCourse(course);
        course.addInstructor(teacher);

        System.out.println("Assigned course " + course.getName() + " to teacher " + teacher.getSurname());
    }

    public void removeCourse(Course course) {
        //
    }

    public void updateCourse(Course course_to_edit, Course new_course) {
        //
    }

    public void createStatisticalReport(University university) {
        // Gather data from university:
        // example: university.getStudents(), calculate average GPA, course pass rates, etc.
        // Print or store the generated report
        System.out.println("Statistical Report:");
        System.out.println("Total Students: " + university.getStudents().size());
        System.out.println("Total Employees: " + university.getEmployees().size());
        // More complex stats for future

    }

    public void manageNews(University university, String news) {
        university.addLog("NEWS: " + news);
        System.out.println("News added: " + news);
        // implement logs (future)
    }

    public void viewStudentsByGPA(University university) {
        List<Student> students = new ArrayList<>(university.getStudents());
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("Students sorted by GPA:");
        for (Student s : students) System.out.println(s.getName() + " " + s.getSurname() + ": " + s.getGpa());
    }

    public void viewTeachersAlphabetically(University university) {
        List<Teacher> teachers = new ArrayList<>(university.getTeachers());
        teachers.sort(Comparator.comparing(Teacher::getSurname));
        System.out.println("Teachers sorted alphabetically by surname:");
        for (Teacher t : teachers) System.out.println(t.getSurname() + ", " + t.getName());
    }
    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
