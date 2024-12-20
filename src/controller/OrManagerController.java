package controller;

import exceptions.CourseRegistrationException;
import model.manager.OrManager;
import model.manager.Request;
import service.OrManagerService;
import view.OrManagerView;

import java.util.List;

public class OrManagerController {
    private final OrManagerService orManagerService;
    private final OrManagerView orManagerView;

    public OrManagerController(OrManagerService orManagerService, OrManagerView orManagerView) {
        this.orManagerService = orManagerService;
        this.orManagerView = orManagerView;
    }

    public void handleOrManagerMenu()  {
        while (true) {
            int choice = orManagerView.displayOrManagerMenu();
            switch (choice) {
                case 1:
                    handleUserManagement();
                    break;
                case 2:
                    handleCourseManagement();
                    break;
                case 3:
                    handleRegistrationManagement();
                    break;
                case 4:
                    handleScheduleManagement();
                    break;
                case 5:
                    orManagerService.createStatisticalReport();
                    orManagerView.displayMessage("Statistical report created.");
                    break;
                case 0:
                    return; // Back to Main Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleUserManagement() {
        while (true) {
            int choice = orManagerView.displayUserManagementMenu();
            switch (choice) {
                case 1:
                    orManagerService.viewUsers();
                    break;
                case 2:
                    orManagerService.updateUser();
                    break;
                case 0:
                    return; // Back to OrManager Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleCourseManagement() {
        while (true) {
            int choice = orManagerView.displayCourseManagementMenu();
            switch (choice) {
                case 1:
//                    orManagerService.addCourse();
                    break;
                case 2:
//                    orManagerService.updateCourse();
                    break;
                case 3:
//                    orManagerService.removeCourse();
                    break;
                case 4:
//                    orManagerService.viewCourse();
                    break;
                case 5:
//                    orManagerService.addCourseSession();
                    break;
                case 6:
//                    orManagerService.assignCourseToTeacher();
                    break;
                case 0:
                    return; // Back to OrManager Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleRegistrationManagement() {
        while (true) {
            int choice = orManagerView.displayRegistrationManagementMenu();
            switch (choice) {
                case 1:
                    viewPendingRequests();
                    break;
                case 2:
                    approveRegistrationRequest();
                    break;
                case 3:
                    rejectRegistrationRequest();
                    break;
                case 0:
                    return; // Back to OrManager Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }
    private void viewPendingRequests() {
        List<Request> pendingRequests = orManagerService.getPendingRequests();
        orManagerView.displayPendingRequests(pendingRequests);
    }

    private void approveRegistrationRequest() {
        int requestId = orManagerView.promptForRequestId();
        orManagerService.approveRegistrationRequest(requestId);
        orManagerView.displayMessage("Request approved.");
    }

    private void rejectRegistrationRequest() {
        int requestId = orManagerView.promptForRequestId();
        orManagerService.rejectRegistrationRequest(requestId);
        orManagerView.displayMessage("Request rejected.");
    }

    private void handleScheduleManagement() {
        while (true) {
            int choice = orManagerView.displayScheduleManagementMenu();
            switch (choice) {
                case 1:
//                    orManagerService.viewCourseSchedule();
                    break;
                case 2:
//                    orManagerService.viewStudentSchedule();
                    break;
                case 3:
//                    orManagerService.viewTeacherSchedule();
                    break;
                case 0:
                    return; // Back to OrManager Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }
}