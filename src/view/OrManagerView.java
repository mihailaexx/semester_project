package view;

import model.academic.Course;
import model.manager.Request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import enums.LESSON_TYPE;
import model.people.Employee;
import model.people.Teacher;
import model.people.Student;

import java.util.List;
import java.util.Scanner;

public class OrManagerView {
    private final Scanner scanner;

    public OrManagerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println("ID: " + student.getStudentID() +
                    ", Name: " + student.getName() +
                    ", Surname: " + student.getSurname() +
                    ", GPA: " + student.getGpa());
        }
    }

    public void displayTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        for (Teacher teacher : teachers) {
            System.out.println("ID: " + teacher.getEmployeeId() +
                    ", Name: " + teacher.getName() +
                    ", Surname: " + teacher.getSurname() +
                    ", Department: " + teacher.getDepartment());
        }
    }

    public void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() +
                    ", Name: " + employee.getName() +
                    ", Surname: " + employee.getSurname() +
                    ", Salary: " + employee.getSalary());
        }
    }
    public int displayOrManagerMenu() {
        System.out.println("\nOR Manager Menu:");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Registration Requests");
        System.out.println("4. View Schedules");
        System.out.println("5. Create Statistical Report");
        System.out.println("6. View Students by GPA");
        System.out.println("7. View Teachers Alphabetically");
        System.out.println("8. View Employees");
        System.out.println("9. View Courses");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int displayUserManagementMenu() {
        System.out.println("\nUser Management:");
        System.out.println("1. View All Users");
        System.out.println("2. Update User Information");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int displayCourseManagementMenu() {
        System.out.println("\nCourse Management:");
        System.out.println("1. Add New Course");
        System.out.println("2. Update Course");
        System.out.println("3. Remove Course");
        System.out.println("4. View Course");
        System.out.println("5. Add Course Session");
        System.out.println("6. Assign Course to Teacher");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int displayRegistrationManagementMenu() {
        System.out.println("\nManage Registration Requests:");
        System.out.println("1. View Pending Requests");
        System.out.println("2. Approve Registration Request");
        System.out.println("3. Reject Registration Request");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    public void displayPendingRequests(List<Request> pendingRequests) {
        System.out.println("\nPending Registration Requests:");
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (Request request : pendingRequests) {
            System.out.println("Request ID: " + request.getRequestId() +
                    ", Student: " + request.getStudent().getName() +
                    ", Course: " + request.getCourse().getName() +
                    ", Status: " + request.getStatus());
        }
    }
    public int promptForRequestId() {
        System.out.print("Enter the ID of the request: ");
        int requestId = scanner.nextInt();
        scanner.nextLine();
        return requestId;
    }
    public int displayScheduleManagementMenu() {
        System.out.println("\nSchedule Management:");
        System.out.println("1. View Course Schedule");
        System.out.println("2. View Student Schedule");
        System.out.println("3. View Teacher Schedule");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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

    public String promptForTeacherId() {
        System.out.print("Enter teacher ID: ");
        return scanner.nextLine();
    }
    public String promptForStudentId() {
        System.out.print("Enter student ID: ");
        return scanner.nextLine();
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
    public void displayCourse(Course course) {
        System.out.println("\nCourse Details:");
        System.out.println("Code: " + course.getCode());
        System.out.println("Name: " + course.getName());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("Major: " + course.getMajor());
        List<Teacher> instructors = course.getInstructors();
        if (!instructors.isEmpty()) {
            System.out.println("Instructors:");
            instructors.forEach(instructor -> System.out.println("- " + instructor.getName() + " " + instructor.getSurname()));
        } else {
            System.out.println("No instructors assigned to this course.");
        }

        if (course.getSchedule() != null) {
            System.out.println("Schedule:");
            course.getSchedule().display();
        } else {
            System.out.println("No schedule set for this course.");
        }
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}