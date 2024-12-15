//package controller;
//
//import model.academic.Course;
//import model.people.Teacher;
//import service.CourseService;
//import view.CourseView; // Assuming you might create a CourseView
//
//public class CourseController {
//    private final CourseService courseService;
//    private final CourseView courseView; // If you create a CourseView
//
//    public CourseController(CourseService courseService, CourseView courseView) {
//        this.courseService = courseService;
//        this.courseView = courseView;
//    }
//
//    // Example method
//    public void addNewCourse(String courseCode, String courseName, int credits, String department) {
//        try {
//            Course course = new Course(courseCode, courseName, credits, department);
//            courseService.addCourse(course);
//            // courseView.displayCourseAdded(course); // If you have a CourseView
//        } catch (Exception e) {
//            // Handle exceptions, possibly display an error message through the view
//        }
//    }
//
//    // ... other methods for managing courses ...
//}