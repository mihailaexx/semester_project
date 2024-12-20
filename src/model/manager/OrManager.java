package model.manager;

import enums.SEX;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.people.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class OrManager extends Employee implements Serializable {
    private static final long serialVersionUID = 8L;

    private List<Request> requests;


    public OrManager(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
    }

    public void addRequest(Request request) throws CourseRegistrationException {
        this.requests.add(request);
    }

    public List<Request> getRequests() {
        return requests;
    }

//    public void approveRegistrationRequest(Student student, Course course) {
//        try {
//            student.registerForCourse(course);
//            System.out.println("Registration request approved for student " + student.getName() + " " + student.getSurname() + " for course " + course.getName());
//        } catch (CourseRegistrationException e) {
//            System.out.println("Could not approve registration request: " + e.getMessage());
//        }
//    }
//
//    public void addCourse(Course course) {
//        // Add course to the in-memory data store or a CourseService
//        System.out.println("Course added: " + course.getName());
//    }
//
//    public void assignCourseToTeacher(Course course, Teacher teacher) {
//        if (teacher.getCourses().contains(course)) {
//            System.out.println("Teacher " + teacher.getName() + " " + teacher.getSurname() + " is already assigned to course " + course.getName());
//        } else {
//            teacher.addCourse(course);
//            course.addInstructor(teacher);
//            System.out.println("Assigned course " + course.getName() + " to teacher " + teacher.getName() + " " + teacher.getSurname());
//        }
//    }
//
//    public void removeCourse(Course course) {
//        // Remove course from the in-memory data store or a CourseService
//        System.out.println("Course removed: " + course.getName());
//    }
//
//    public void updateCourse(Course oldCourse, Course newCourse) {
//        // Update course in the in-memory data store or a CourseService
//        System.out.println("Course updated: " + oldCourse.getName() + " to " + newCourse.getName());
//    }
//
//    public void createStatisticalReport() {
//        // This method now assumes the data is managed by DataStore
//        System.out.println("Statistical Report:");
//
//        // Get the data from DataStore
//        List<Student> students = dataStore.getAllStudents();
//        List<Teacher> teachers = dataStore.getAllTeachers();
//        List<Employee> employees = dataStore.getAllEmployees();
//
//        // Student statistics
//        System.out.println("\nStudent Statistics:");
//        System.out.println("Total Students: " + students.size());
//        if (!students.isEmpty()) {
//            double averageGpa = students.stream()
//                    .mapToDouble(Student::getGpa)
//                    .average()
//                    .orElse(0.0);
//            System.out.printf("Average GPA: %.2f\n", averageGpa);
//        }
//
//        // Teacher statistics
//        System.out.println("\nTeacher Statistics:");
//        System.out.println("Total Teachers: " + teachers.size());
//        if (!teachers.isEmpty()) {
//            double averageRating = teachers.stream()
//                    .mapToDouble(Teacher::getAverageRating)
//                    .average()
//                    .orElse(0.0);
//            System.out.printf("Average Rating: %.2f\n", averageRating);
//        }
//
//        // Employee statistics
//        System.out.println("\nEmployee Statistics:");
//        System.out.println("Total Employees: " + employees.size());
//
//        // Additional statistics can be added here
//    }
//
//    public void viewStudentsByGPA() {
//        List<Student> students = dataStore.getAllStudents();
//        students.sort(Comparator.comparing(Student::getGpa).reversed());
//
//        System.out.println("Students sorted by GPA (highest to lowest):");
//        for (Student student : students) {
//            System.out.println(student.getName() + " " + student.getSurname() + " - GPA: " + student.getGpa());
//        }
//    }
//
//    public void viewTeachersAlphabetically() {
//        List<Teacher> teachers = dataStore.getAllTeachers();
//        teachers.sort(Comparator.comparing(Teacher::getSurname).thenComparing(Teacher::getName));
//
//        System.out.println("Teachers sorted alphabetically (by surname, then name):");
//        for (Teacher teacher : teachers) {
//            System.out.println(teacher.getSurname() + ", " + teacher.getName());
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrManager)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "OrManager{" +
                "employeeId=" + getEmployeeId() +
                ", salary=" + getSalary() +
                ", hireDate=" + getHireDate() +
                "} " + super.toString();
    }
}