package controller;

import model.manager.OrManager;
import service.OrManagerService;
import view.OrManagerView;

public class OrManagerController {
    private final OrManagerService orManagerService;
    private final OrManagerView orManagerView;

    public OrManagerController(OrManagerService orManagerService, OrManagerView orManagerView) {
        this.orManagerService = orManagerService;
        this.orManagerView = orManagerView;
    }

    public void handleOrManagerMenu(OrManager orManager) {
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
                    return;
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
                    return;
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
                    orManagerService.addNewCourse();
                    break;
                case 2:
                    orManagerService.updateCourse();
                    break;
                case 3:
                    orManagerService.removeCourse();
                    break;
                case 4:
                    orManagerService.viewCourse();
                    break;
                case 5:
                    orManagerService.addCourseSession();
                case 6:
                    orManagerService.assignCourseToTeacher();
                case 0:
                    return;
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
                    orManagerService.approveRegistrationRequest();
                    break;
                case 2:
                    orManagerService.rejectRegistrationRequest();
                    break;
                case 0:
                    return;
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleScheduleManagement() {
        while (true) {
            int choice = orManagerView.displayScheduleManagementMenu();
            switch (choice) {
                case 1:
                    orManagerService.viewCourseSchedule();
                    break;
                case 2:
                    orManagerService.viewStudentSchedule();
                    break;
                case 3:
                    orManagerService.viewTeacherSchedule();
                    break;
                case 0:
                    return;
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }
}