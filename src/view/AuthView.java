package view;

import java.util.Scanner;

public class AuthView {
    private final Scanner scanner;

    public AuthView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] promptForCredentials() {
        System.out.println("Please enter your credentials.");
        scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new String[]{username, password};
    }

    public String[] promptForUserDetails() {
        System.out.println("Select user type to sign up:");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Employee");
        System.out.println("4. OrManager"); //Add OrManager,FinanceManager
        //Add OrManager,FinanceManager
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return promptForStudentDetails();
            case 2:
                return promptForTeacherDetails();
            case 3:
                return promptForEmployeeDetails();
            case 4:
                return promptForOrManagerDetails();
            default:
                System.out.println("Invalid choice. Please try again.");
                return promptForUserDetails();
        }
    }
    public String[] promptForStudentDetails() {
        System.out.println("Please enter your details for signing up as a student.");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Sex (MALE/FEMALE): ");
        String sex = scanner.nextLine().toUpperCase();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Major: ");
        String major = scanner.nextLine();

        System.out.print("Student Type (GRANT, PAID, EXCHANGE): ");
        String studentType = scanner.nextLine().toUpperCase();

        System.out.print("Year of Study: ");
        String yearOfStudy = scanner.nextLine();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        return new String[]{"STUDENT",firstName, lastName, sex, phoneNumber, citizenship, email, password, major, studentType, yearOfStudy, dateOfBirth};
    }

    private String[] promptForTeacherDetails() {
        System.out.println("Please enter your details for signing up as a teacher.");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Sex (MALE/FEMALE): ");
        String sex = scanner.nextLine().toUpperCase();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Teacher Degree (e.g., TUTOR, LECTURER, PROFESSOR): ");
        String teacherDegree = scanner.nextLine().toUpperCase();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        return new String[]{"TEACHER", firstName, lastName, sex, phoneNumber, citizenship, email, password, department, teacherDegree, String.valueOf(salary), dateOfBirth};
    }
    private String[] promptForEmployeeDetails() {
        System.out.println("Please enter your details for signing up as an employee.");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Sex (MALE/FEMALE): ");
        String sex = scanner.nextLine().toUpperCase();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        return new String[]{"EMPLOYEE", firstName, lastName, sex, email, password, phoneNumber, citizenship, String.valueOf(salary), dateOfBirth};
    }

    public String[] promptForOrManagerDetails() {
        System.out.println("Please enter your details for signing up as an OR Manager.");
        scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Sex (MALE/FEMALE): ");
        String sex = scanner.nextLine().toUpperCase();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        return new String[]{"ORMANAGER", firstName, lastName, sex, email, password, phoneNumber, citizenship, String.valueOf(salary), dateOfBirth};
    }
    public void displayLoginSuccess(String username) {
        System.out.println("Login successful! Welcome, " + username + "!");
    }

    public void displayLoginFailure() {
        System.out.println("Login failed. Invalid username or password.");
    }

    public void displaySignupSuccess() {
        System.out.println("Signup successful! You can now log in.");
    }

    public void displaySignupFailure(String reason) {
        System.out.println("Signup failed: " + reason);
    }

    public void displayErrorMessage(String s) {
        System.err.println("Error: " + s);
    }
}