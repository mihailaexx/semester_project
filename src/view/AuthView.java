package view;

import java.util.Scanner;

public class AuthView {
    private final Scanner scanner;

    public AuthView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] promptForCredentials() {
        System.out.println("Please enter your credentials.");
        scanner.nextLine(); // Consume any leftover newline
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new String[]{username, password};
    }

    public String[] promptForStudentDetails() {
        System.out.println("Please enter your details for signing up as a student.");
        scanner.nextLine(); // Consume any leftover newline
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

        // Add user type
        return new String[]{firstName, lastName, sex, phoneNumber, citizenship, email, password, major, studentType, yearOfStudy, dateOfBirth};
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
}