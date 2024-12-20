package view;

import model.people.Student;
import model.people.Teacher;
import model.people.Employee;
import model.people.User;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner;

    public UserView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users to display.");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    public String promptForUsername() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    public String[] promptForUserDetails(User user) {
        String[] details = new String[10];
        System.out.println("Updating details for user: " + user.getUsername());
        System.out.println("Leave field blank to keep current value.");

        System.out.print("First Name [" + user.getName() + "]: ");
        details[0] = scanner.nextLine();

        System.out.print("Last Name [" + user.getSurname() + "]: ");
        details[1] = scanner.nextLine();

        System.out.print("Sex (MALE/FEMALE) [" + user.getSex() + "]: ");
        details[2] = scanner.nextLine().toUpperCase();

        System.out.print("Phone Number [" + user.getPhoneNumber() + "]: ");
        details[3] = scanner.nextLine();

        System.out.print("Citizenship [" + user.getCitizenship() + "]: ");
        details[4] = scanner.nextLine();

        System.out.print("Email [" + user.getEmail() + "]: ");
        details[5] = scanner.nextLine();

        if (user instanceof Student) {
            Student student = (Student) user;
            System.out.print("Major [" + student.getMajor() + "]: ");
            details[7] = scanner.nextLine();

            System.out.print("Student Type (GRANT, PAID, EXCHANGE) [" + student.getType() + "]: ");
            details[8] = scanner.nextLine().toUpperCase();

            System.out.print("Year of Study [" + student.getYearOfStudy() + "]: ");
            details[9] = scanner.nextLine();
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            System.out.print("Department [" + teacher.getDepartment() + "]: ");
            details[7] = scanner.nextLine();

            System.out.print("Teacher Degree (e.g., TUTOR, LECTURER, PROFESSOR) [" + teacher.getTeacherDegree() + "]: ");
            details[8] = scanner.nextLine().toUpperCase();

            System.out.print("Salary [" + teacher.getSalary() + "]: ");
            details[9] = scanner.nextLine();
        } else if (user instanceof Employee) {
            Employee employee = (Employee) user;
            System.out.print("Salary [" + employee.getSalary() + "]: ");
            details[7] = scanner.nextLine();
        }

        return details;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}