package view;

import enums.LESSON_TYPE;
import model.academic.Course;
import model.academic.Schedule;
import model.people.Teacher;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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

    public int displayCourseMenu() {
        System.out.println("\nCourse Management Menu:");
        System.out.println("1. Add New Course");
        System.out.println("2. Update Course");
        System.out.println("3. Remove Course");
        System.out.println("4. View Course");
        System.out.println("5. Add course session");
        System.out.println("0. Back to Admin Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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

    public LESSON_TYPE promptForLessonType() {
        System.out.print("Enter lesson type (LECTURE, PRACTICE, LAB): ");
        while (true) {
            try {
                return LESSON_TYPE.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid lesson type. Please enter LECTURE, PRACTICE, or LAB.");
            }
        }
    }

    public DayOfWeek promptForDayOfWeek() {
        System.out.print("Enter day of the week (e.g., MONDAY, TUESDAY, etc.): ");
        while (true) {
            try {
                return DayOfWeek.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day of week. Please enter a valid day.");
            }
        }
    }

    public LocalTime promptForTime() {
        System.out.print("Enter time (HH:mm): ");
        while (true) {
            try {
                return LocalTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    public void displaySchedule(Schedule schedule) {
        System.out.println("Course Schedule:");
        if (schedule == null) {
            System.out.println("No schedule to display.");
            return;
        }
        schedule.display();
    }
}