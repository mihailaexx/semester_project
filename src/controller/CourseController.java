package controller;

import enums.LESSON_TYPE;
import model.academic.Course;
import model.people.Teacher;
import service.CourseService;
import view.CourseView;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class CourseController {
    private final CourseService courseService;
    private final CourseView courseView;

    public CourseController(CourseService courseService, CourseView courseView) {
        this.courseService = courseService;
        this.courseView = courseView;
    }

    public void addNewCourse() {
        String code = courseView.promptForCourseCode();
        String name = courseView.promptForCourseName();
        int credits = courseView.promptForCredits();
        String major = courseView.promptForMajor();

        try {
            Course course = new Course(code, name, credits, major);
            courseService.addCourse(course);
            courseView.displayCourseAdded(course);
        } catch (Exception e) {
            courseView.displayErrorMessage("Failed to add course. " + e.getMessage());
        }
    }

    public void updateCourse(String courseCode) {
        Course course = courseService.getCourseByCode(courseCode);
        if (course == null) {
            courseView.displayErrorMessage("Course not found.");
            return;
        }

        String name = courseView.promptForCourseName();
        int credits = courseView.promptForCredits();
        String major = courseView.promptForMajor();

        // Update the course object
        course.setName(name);
        course.setCredits(credits);
        course.setMajor(major);

        try {
            courseService.updateCourse(course);
            courseView.displayCourseUpdated(course);
        } catch (Exception e) {
            courseView.displayErrorMessage("Failed to update course. " + e.getMessage());
        }
    }

    public void removeCourse(String courseCode) {
        try {
            courseService.removeCourse(courseCode);
            courseView.displayCourseRemoved(courseCode);
        } catch (Exception e) {
            courseView.displayErrorMessage("Failed to remove course. " + e.getMessage());
        }
    }

    public void viewCourse(String courseCode) {
        Course course = courseService.getCourseByCode(courseCode);
        if (course != null) {
            courseView.displayCourse(course);
        } else {
            courseView.displayErrorMessage("Course not found.");
        }
    }

    public void addCourseSession(String courseCode) {
        Course course = courseService.getCourseByCode(courseCode);
        if (course == null) {
            courseView.displayErrorMessage("Course not found: " + courseCode);
            return;
        }

        try {
            LESSON_TYPE lessonType = courseView.promptForLessonType();
            DayOfWeek dayOfWeek = courseView.promptForDayOfWeek();
            LocalTime startTime = courseView.promptForTime();

            courseService.addCourseSession(course.getCode(), lessonType, dayOfWeek, startTime);
            courseView.displayMessage("Course session added successfully.");
        } catch (IllegalArgumentException e) {
            courseView.displayErrorMessage("Invalid input: " + e.getMessage());
        } catch (DateTimeParseException e) {
            courseView.displayErrorMessage("Invalid time format. Use HH:mm");
        } catch (Exception e) {
            courseView.displayErrorMessage("Failed to add course session: " + e.getMessage());
        }
    }
}