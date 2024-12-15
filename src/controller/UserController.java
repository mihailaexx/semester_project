package controller;

import exceptions.AuthenticationException;
import exceptions.InvalidInputException;
import model.people.Student;
import model.people.Teacher;
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

    public void handleSignup(AuthView authView, String[] details) throws InvalidInputException {
        userService.createStudent(details);
        authView.displaySignupSuccess();
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        userService.registerStudentForCourse(student, courseCode);
    }

    public void createStudent(String[] details) {

    }

    // Other general user-related methods can be added here
    // Removed authenticateUser and createStudent because they are now duplicates of handleLogin and handleSignup
}