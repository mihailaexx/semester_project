package view;

import java.util.Scanner;

public class OrManagerView {
    private final Scanner scanner;

    public OrManagerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayOrManagerMenu() {
        System.out.println("\nOR Manager Menu:");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Registration Requests");
        System.out.println("4. View Schedules");
        System.out.println("5. Create Statistical Report");
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
        System.out.println("1. Approve Registration Request");
        System.out.println("2. Reject Registration Request");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}