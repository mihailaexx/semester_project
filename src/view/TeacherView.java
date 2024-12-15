package view;

import data.DataStore;
import model.academic.Course;
import model.academic.Mark;
import model.people.Student;
import model.people.Teacher;

import java.util.List;
import java.util.Scanner;

public class TeacherView {
    private final Scanner scanner;

    public TeacherView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayTeacherMenu() {
        System.out.println("\nTeacher Menu:");
        System.out.println("1. View Courses");
        System.out.println("2. View Students in Course");
        System.out.println("3. Put Mark for Student");
        System.out.println("4. Send Complaint");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void displayCourses(List<Course> courses) {
        System.out.println("\nCourses:");
        if (courses.isEmpty()) {
            System.out.println("No courses to display.");
        } else {
            for (Course course : courses) {
                System.out.println(course); // Assuming Course.toString() provides a good representation
            }
        }
    }

    public String promptForCourseSelection() {
        scanner.nextLine();
        System.out.print("\nEnter the code of the course: ");
        return scanner.nextLine();
    }

    public void displayStudents(Teacher teacher, String courseCode, DataStore dataStore) {
        System.out.println("\nStudents in course " + courseCode + ":");
        Course selectedCourse = teacher.getCourses().stream()
                .filter(c -> c.getCode().equals(courseCode))
                .findFirst()
                .orElse(null);

        if (selectedCourse != null) {
            List<Student> students = dataStore.getAllStudents(); // Assuming this method exists
            students.stream()
                    .filter(s -> s.getCourses().contains(selectedCourse))
                    .forEach(System.out::println); // Assuming Student.toString() is implemented
        } else {
            System.out.println("Course not found or teacher is not assigned to this course.");
        }
    }

    public String promptForStudentSelection() {
        scanner.nextLine();
        System.out.print("\nEnter the ID of the student: ");
        return scanner.nextLine();
    }

    public double promptForMark(String markType) {
        System.out.print("Enter " + markType + " mark: ");
        return scanner.nextDouble();
    }

    public void displayMarkInputSuccess() {
        System.out.println("Mark input successful.");
    }

    public void displayMarkInputFailure(String reason) {
        System.out.println("Mark input failed: " + reason);
    }

    public String[] promptForComplaint() {
        scanner.nextLine();
        System.out.print("Enter complaint message: ");
        String message = scanner.nextLine();
        System.out.print("Enter urgency (LOW, MEDIUM, HIGH): ");
        String urgency = scanner.nextLine().toUpperCase();
        return new String[]{message, urgency};
    }

    public void displayComplaintSent() {
        System.out.println("Complaint sent successfully.");
    }

    public void displayComplaintFailure(String reason) {
        System.out.println("Complaint sending failed: " + reason);
    }

    public void displayMarkInputForm(Teacher teacher, DataStore dataStore) {

    }

    public void displayComplaintForm() {
    }
}