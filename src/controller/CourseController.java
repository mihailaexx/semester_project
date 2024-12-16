package controller;

import model.academic.Course;
import model.people.Teacher;
import service.CourseService;
import view.CourseView;

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

        // Prompt for new course details (or modify as needed)
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

    // Add other methods for handling course-related actions
}