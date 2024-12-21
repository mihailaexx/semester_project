package controller;

import enums.LESSON_TYPE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.manager.OrManager;
import model.manager.Request;
import service.CourseService;
import service.OrManagerService;
import service.TeacherService;
import view.OrManagerView;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class OrManagerController {
    private final OrManagerService orManagerService;
    private final OrManagerView orManagerView;
    private final CourseService courseService;

    public OrManagerController(OrManagerService orManagerService, OrManagerView orManagerView, CourseService courseService) {
        this.orManagerService = orManagerService;
        this.orManagerView = orManagerView;
        this.courseService = courseService;
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
                case 6:
                    orManagerService.viewStudentsByGPA();
                    break;
                case 7:
                    orManagerService.viewTeachersAlphabetically();
                    break;
                case 8:
                    orManagerService.viewEmployees();
                    break;
                case 9:
                    orManagerService.viewCourses();
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
                    addNewCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    viewCourse();
                    break;
                case 5:
                    addCourseSession();
                    break;
                case 6:
                    assignCourseToTeacher();
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

    public void addNewCourse() {
        String code = orManagerView.promptForCourseCode();
        String name = orManagerView.promptForCourseName();
        int credits = orManagerView.promptForCredits();
        String major = orManagerView.promptForMajor();

        try {
            Course course = new Course(code, name, credits, major);
            orManagerService.addCourse(course);
            orManagerView.displayMessage("Course added successfully.");
        } catch (Exception e) {
            orManagerView.displayErrorMessage("Failed to add course: " + e.getMessage());
        }
    }

    public void updateCourse() {
        String courseCode = orManagerView.promptForCourseCode();
        Course course = courseService.getCourseByCode(courseCode);

        if (course == null) {
            orManagerView.displayErrorMessage("Course not found.");
            return;
        }

        String name = orManagerView.promptForCourseName();
        int credits = orManagerView.promptForCredits();
        String major = orManagerView.promptForMajor();

        course.setName(name);
        course.setCredits(credits);
        course.setMajor(major);

        try {
            orManagerService.updateCourse(courseCode);
            orManagerView.displayMessage("Course updated successfully.");
        } catch (Exception e) {
            orManagerView.displayErrorMessage("Failed to update course: " + e.getMessage());
        }
    }

    public void removeCourse() {
        String courseCode = orManagerView.promptForCourseCode();
        try {
            orManagerService.removeCourse(courseCode);
            orManagerView.displayMessage("Course removed successfully.");
        } catch (Exception e) {
            orManagerView.displayErrorMessage("Failed to remove course: " + e.getMessage());
        }
    }

    public void viewCourse() {
        String courseCode = orManagerView.promptForCourseCode();
        orManagerService.viewCourse(courseCode);
    }

    public void addCourseSession() {
        String courseCode = orManagerView.promptForCourseCode();
        Course course = orManagerService.getCourseByCode(courseCode);
        if (course == null) {
            orManagerView.displayErrorMessage("Course not found: " + courseCode);
            return;
        }

        try {
            LESSON_TYPE lessonType = orManagerView.promptForLessonType();
            DayOfWeek dayOfWeek = orManagerView.promptForDayOfWeek();
            LocalTime startTime = orManagerView.promptForTime();

            orManagerService.addCourseSession(course, lessonType, dayOfWeek, startTime);
            orManagerView.displayMessage("Course session added successfully.");
        } catch (IllegalArgumentException e) {
            orManagerView.displayErrorMessage("Invalid input: " + e.getMessage());
        }
    }

    public void assignCourseToTeacher() {
        String courseCode = orManagerView.promptForCourseCode();
        String teacherId = orManagerView.promptForTeacherId();

        try {
            orManagerService.assignCourseToTeacher(courseCode, teacherId);
            orManagerView.displayMessage("Course assigned to teacher successfully.");
        } catch (NumberFormatException e) {
            orManagerView.displayErrorMessage("Invalid teacher ID format.");
        } catch (Exception e) {
            orManagerView.displayErrorMessage("Failed to assign course to teacher: " + e.getMessage());
        }
    }

    public void viewCourseSchedule() {
        String courseCode = orManagerView.promptForCourseCode();
        orManagerService.viewCourseSchedule(courseCode);
    }
    public void viewStudentSchedule() {
        String studentId = orManagerView.promptForStudentId();
        orManagerService.viewStudentSchedule(studentId);
    }
    public void viewTeacherSchedule() {
        String teacherId = orManagerView.promptForTeacherId();
        try {
            orManagerService.viewTeacherSchedule(teacherId);
        } catch (NumberFormatException e) {
            orManagerView.displayErrorMessage("Invalid teacher ID format.");
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
                    viewCourseSchedule();
                    break;
                case 2:
                    viewStudentSchedule();
                    break;
                case 3:
                    viewTeacherSchedule();
                    break;
                case 0:
                    return; // Back to OrManager Menu
                default:
                    orManagerView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }
}