package controller;

import exceptions.AuthenticationException;
import exceptions.InvalidInputException;
import model.people.Student;
import model.people.User;
import service.UserService;
import view.AuthView;
import view.CLI;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void handleLogin(AuthView authView, CLI cli) {
        String[] credentials = authView.promptForCredentials();
        try {
            User user = userService.authenticateUser(credentials[0], credentials[1]);
            if (user != null) {
                authView.displayLoginSuccess(user.getUsername());
                cli.showUserMenu(user);
            } else {
                authView.displayLoginFailure();
            }
        } catch (AuthenticationException e) {
            cli.displayErrorMessage(e.getMessage());
        }
    }

    public void handleSignup(AuthView authView, String[] details) {
        try {
            switch (details[0]) {
                case "STUDENT":
                    userService.createStudent(details);
                    authView.displaySignupSuccess();
                    break;
                case "TEACHER":
                    userService.createTeacher(details);
                    authView.displaySignupSuccess();
                    break;
                case "EMPLOYEE":
                    // Handle employee creation similarly
                    break;
                default:
                    authView.displayErrorMessage("Invalid user type for signup.");
            }
        } catch (InvalidInputException e) {
            authView.displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            authView.displayErrorMessage("An error occurred during signup.");
        }
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        userService.registerStudentForCourse(student, courseCode);
    }
}