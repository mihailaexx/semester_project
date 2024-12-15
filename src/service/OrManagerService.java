package service;

import data.DataStore;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;

import java.util.Comparator;
import java.util.List;

public class OrManagerService {
    private final DataStore dataStore;

    public OrManagerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void approveRegistrationRequest(Student student, Course course) {
        try {
            student.registerForCourse(course);
            dataStore.saveStudent(student); // Assuming DataStore has a method to save or update student
            System.out.println("Registration request approved for student " + student.getName() + " " + student.getSurname() + " for course " + course.getName());
        } catch (CourseRegistrationException e) {
            System.out.println("Could not approve registration request: " + e.getMessage());
        }
    }

    public void addCourse(Course course) {
        dataStore.saveCourse(course);
        System.out.println("Course added: " + course.getName());
    }

    public void assignCourseToTeacher(Course course, Teacher teacher) {
        if (teacher.getCourses().contains(course)) {
            System.out.println("Teacher " + teacher.getName() + " " + teacher.getSurname() + " is already assigned to course " + course.getName());
        } else {
            teacher.addCourse(course);
            dataStore.saveTeacher(teacher);
            System.out.println("Assigned course " + course.getName() + " to teacher " + teacher.getName() + " " + teacher.getSurname());
        }
    }

    public void removeCourse(Course course) {
        dataStore.getAllCourses().remove(course);
        System.out.println("Course removed: " + course.getName());
    }

    public void updateCourse(Course oldCourse, Course newCourse) {
        dataStore.saveCourse(newCourse);
        System.out.println("Course updated: " + oldCourse.getName() + " to " + newCourse.getName());
    }

    public void createStatisticalReport() {
        System.out.println("Statistical Report:");

        List<Student> students = dataStore.getAllStudents();
        List<Teacher> teachers = dataStore.getAllTeachers();
        List<Employee> employees = dataStore.getAllEmployees();

        System.out.println("\nStudent Statistics:");
        System.out.println("Total Students: " + students.size());
        if (!students.isEmpty()) {
            double averageGpa = students.stream()
                    .mapToDouble(Student::getGpa)
                    .average()
                    .orElse(0.0);
            System.out.printf("Average GPA: %.2f\n", averageGpa);
        }

        System.out.println("\nTeacher Statistics:");
        System.out.println("Total Teachers: " + teachers.size());
        if (!teachers.isEmpty()) {
            double averageRating = teachers.stream()
                    .mapToDouble(Teacher::getAverageRating)
                    .average()
                    .orElse(0.0);
            System.out.printf("Average Rating: %.2f\n", averageRating);
        }

        System.out.println("\nEmployee Statistics:");
        System.out.println("Total Employees: " + employees.size());
    }

    public void viewStudentsByGPA() {
        List<Student> students = dataStore.getAllStudents();
        students.sort(Comparator.comparing(Student::getGpa).reversed());

        System.out.println("Students sorted by GPA (highest to lowest):");
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getSurname() + " - GPA: " + student.getGpa());
        }
    }

    public void viewTeachersAlphabetically() {
        List<Teacher> teachers = dataStore.getAllTeachers();
        teachers.sort(Comparator.comparing(Teacher::getSurname).thenComparing(Teacher::getName));

        System.out.println("Teachers sorted alphabetically (by surname, then name):");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getSurname() + ", " + teacher.getName());
        }
    }
}