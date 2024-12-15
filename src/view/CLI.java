package view;

import controller.StudentController;
import controller.TeacherController;
import controller.UserController;
import data.DataStore;
import data.InMemoryDataStore;
import exceptions.AuthenticationException;
import exceptions.InvalidInputException;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import service.StudentService;
import service.TeacherService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private final Scanner scanner;
    private final UserController userController;
    private final StudentController studentController;
    private final TeacherController teacherController;
    private final AuthView authView;
    private final StudentView studentView;
    private final TeacherView teacherView;
    private DataStore dataStore;
    private UserService userService;
    private StudentService studentService;
    private TeacherService teacherService;

    public CLI() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new InMemoryDataStore();
        this.userService = new UserService(dataStore);
        this.userController = new UserController(userService);
        this.authView = new AuthView(scanner);
        this.studentView = new StudentView(scanner);
        this.teacherView = new TeacherView(scanner);
        this.studentService = new StudentService(dataStore);
        this.teacherService = new TeacherService(dataStore);
        this.studentController = new StudentController(studentService, null, studentView);
        this.teacherController = new TeacherController(teacherService, null, teacherView);
    }

    public void run() {
        displayWelcomeMessage();

        while (true) {
            int choice = promptLoginOrSignup();

            if (choice == 1) {
                handleLogin();
            } else if (choice == 2) {
                handleSignup();
            } else if (choice == 3) {
                dataStore.saveData(); // Save data option
                System.out.println("Data saved successfully.");
            } else if (choice == 0) {
                break;
            } else {
                displayInvalidChoice();
            }
        }
        displayExitMessage();
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to the KBTU University Information System!");
    }

    private int promptLoginOrSignup() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Save data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void handleLogin() {
        String[] credentials = authView.promptForCredentials();
        try {
            User user = userService.authenticateUser(credentials[0], credentials[1]);
            if (user != null) {
                authView.displayLoginSuccess(user.getUsername());
                showUserMenu(user);
            } else {
                authView.displayLoginFailure();
            }
        } catch (AuthenticationException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    private void handleSignup() {
        String[] details = authView.promptForStudentDetails();
        try {
            userController.handleSignup(authView, details);
        } catch (InvalidInputException e) {
            displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            displayErrorMessage("An error occurred during signup.");
        }
    }

    public void showUserMenu(User user) {
        if (user instanceof Student) {
            showStudentMenu((Student) user);
        } else if (user instanceof Teacher) {
            showTeacherMenu((Teacher) user);
        }
    }

    private void showStudentMenu(Student student) {
        while (true) {
            int choice = studentView.displayStudentMenu();
            switch (choice) {
                case 1:
                    studentController.viewStudentTranscript(student);
                    break;
                case 2:
                    studentController.viewStudentCourses(student);
                    break;
                case 3:
                    String courseCode = studentView.promptForCourseRegistration();
                    studentController.registerStudentForCourse(student, courseCode);
                    break;
                case 4:
                    studentController.viewMarks(student);
                    break;
                case 5:
                    studentView.displayRateTeacherForm(student);
                    break;
                case 0:
                    return; // Return to the main menu
                default:
                    displayInvalidChoice();
            }
        }
    }

    private void showTeacherMenu(Teacher teacher) {
        while (true) {
            int choice = teacherView.displayTeacherMenu();
            switch (choice) {
                case 1:
                    teacherController.viewTeacherCourses(teacher);
                    break;
                case 2:
                    String courseCode = teacherView.promptForCourseSelection();
                    teacherController.viewStudentsInCourse(teacher, courseCode, dataStore);
                    break;
                case 3:
                    teacherView.displayMarkInputForm(teacher, dataStore);
                    break;
                case 4:
                    teacherView.displayComplaintForm();
                    break;
                case 0:
                    return; // Return to the main menu
                default:
                    displayInvalidChoice();
            }
        }
    }

    private void displayInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    private void displayExitMessage() {
        System.out.println("Exiting the application. Goodbye!");
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.run();
    }
}