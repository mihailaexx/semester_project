package view;

import controller.*;
import data.DataStore;
import data.InMemoryDataStore;
import exceptions.AuthenticationException;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.manager.FinanceManager;
import model.manager.OrManager;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import service.*;

import java.util.Scanner;

public class CLI {
    private final Scanner scanner;
    private final UserController userController;
    private final StudentController studentController;
    private final TeacherController teacherController;
    private final EmployeeController employeeController;
    private final OrManagerController orManagerController;
    private final AuthView authView;
    private final StudentView studentView;
    private final TeacherView teacherView;
    private final EmployeeView employeeView;
    private final OrManagerView orManagerView;
    private final UserView userView;
    private DataStore dataStore;
    private UserService userService;
    private StudentService studentService;
    private TeacherService teacherService;
    private OrManagerService orManagerService;
    private final MessageService messageService;
    private final CourseService courseService;
    private final CourseController courseController;
    private final CourseView courseView;

    public CLI() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new InMemoryDataStore();
        this.userService = new UserService(dataStore);
        this.userController = new UserController(userService);
        this.authView = new AuthView(scanner);
        this.courseService = new CourseService(dataStore);
        this.courseView = new CourseView(scanner);
        this.teacherService = new TeacherService(dataStore);
        this.courseController = new CourseController(courseService, courseView);
        this.studentView = new StudentView(scanner, courseView, teacherService);
        this.teacherView = new TeacherView(scanner, courseView);
        this.userView = new UserView(scanner);
        this.studentService = new StudentService(dataStore, orManagerService);
        this.studentController = new StudentController(studentService, courseService, studentView);
        this.teacherController = new TeacherController(teacherService, courseService, teacherView, dataStore);
        this.employeeView = new EmployeeView(scanner);
        this.orManagerView = new OrManagerView(scanner);
        this.messageService = new MessageService(dataStore);
        this.employeeController = new EmployeeController(messageService, employeeView);
        this.orManagerService = new OrManagerService(dataStore, userView, courseView, studentView, teacherView, orManagerView);
        this.orManagerController = new OrManagerController(orManagerService, orManagerView, courseService);
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
                dataStore.saveData();
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
        System.out.println("Welcome to the KBTU University!");
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

    private void handleSignup()  {
        String[] details = authView.promptForUserDetails();
        userController.handleSignup(authView, details);
    }

    public void showUserMenu(User user)  {
        if (user instanceof Student) {
            showStudentMenu((Student) user);
        } else if (user instanceof Teacher) {
            showTeacherMenu((Teacher) user);
        } else if (user instanceof Employee && !(user instanceof OrManager) && !(user instanceof FinanceManager)) {
            showEmployeeMenu((Employee) user);
        } else if (user instanceof OrManager) {
            showOrManagerMenu((OrManager) user);
        } else if (user instanceof FinanceManager) {
            showFinanceManagerMenu((FinanceManager) user);
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
                    studentView.displayRateTeacherForm(student, studentController);
                    break;
                case 6:
                    studentController.viewStudentSchedule(student);
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
                    teacherView.displayMarkInputForm(teacher, dataStore, teacherController);
                    break;
                case 4:
                    String[] complaintDetails = teacherView.promptForComplaint();
                    teacherController.sendComplaint(teacher, complaintDetails[0], complaintDetails[1], complaintDetails[2]);
                    break;
                case 5:
                    teacherController.viewTeacherSchedule(teacher);
                    break;
                case 6:
                    String recipientUsername = employeeView.promptForRecipientUsername();
                    String messageText = employeeView.promptForMessageText();
                    employeeController.handleSendMessage(teacher, recipientUsername, messageText);
                case 7:
                    employeeView.displayMessages(teacher);
                    break;
                case 0:
                    return; // Return to the main menu
                default:
                    displayInvalidChoice();
            }
        }
    }

    private void showEmployeeMenu(Employee employee) {
        while (true) {
            int choice = employeeView.displayEmployeeMenu();
            switch (choice) {
                case 1:
                    String recipientUsername = employeeView.promptForRecipientUsername();
                    String messageText = employeeView.promptForMessageText();
                    employeeController.handleSendMessage(employee, recipientUsername, messageText);
                    break;
                case 2:
                    employeeView.displayMessages(employee);
                    break;
                case 0:
                    return;
                default:
                    displayInvalidChoice();
            }
        }
    }

    private void showOrManagerMenu(OrManager orManager)  {
        orManagerController.handleOrManagerMenu();
    }

    private void showFinanceManagerMenu(FinanceManager user) {
        //Implement
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