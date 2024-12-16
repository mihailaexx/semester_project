package view;

import model.academic.Course;
import model.people.Teacher;

import java.util.List;
import java.util.Scanner;

public class CourseView {
    private final Scanner scanner;

    public CourseView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayCourse(Course course) {
        System.out.println("Course Details:");
        System.out.println("Code: " + course.getCode());
        System.out.println("Name: " + course.getName());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("Major: " + course.getMajor());
        System.out.println("Instructors: " + course.getInstructors().stream()
                .map(Teacher::getName)
                .toList());
    }

    public void displayCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            displayCourse(course);
        }
    }

    public String promptForCourseCode() {
        System.out.print("Enter course code: ");
        return scanner.nextLine();
    }

    public String promptForCourseName() {
        System.out.print("Enter course name: ");
        return scanner.nextLine();
    }

    public int promptForCredits() {
        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        return credits;
    }

    public String promptForMajor() {
        System.out.print("Enter course major: ");
        return scanner.nextLine();
    }

    public void displayCourseAdded(Course course) {
        System.out.println("Course added successfully: " + course.getName() + " (" + course.getCode() + ")");
    }

    public void displayCourseUpdated(Course course) {
        System.out.println("Course updated successfully: " + course.getName() + " (" + course.getCode() + ")");
    }

    public void displayCourseRemoved(String courseCode) {
        System.out.println("Course removed successfully: " + courseCode);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    // Add other methods as needed for displaying course-related information
}