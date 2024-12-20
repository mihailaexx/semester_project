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
                    userService.createEmployee(details);
                    authView.displaySignupSuccess();
                    break;
                case "ORMANAGER":
                    userService.createOrManager(details);
                    authView.displaySignupSuccess();
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
}