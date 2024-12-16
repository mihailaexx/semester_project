package controller;

import model.people.Student;
import service.CourseService;
import service.StudentService;
import view.StudentView;

public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final StudentView studentView;

    public StudentController(StudentService studentService, CourseService courseService, StudentView studentView) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentView = studentView;
    }

    public void viewStudentTranscript(Student student) {
        studentView.displayTranscript(student);
    }

    public void viewStudentCourses(Student student) {
        studentView.displayCourses(student.getEnrolledCourses());
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        try {
            courseService.registerStudentForCourse(student, courseCode);
            studentView.displayRegistrationSuccess(courseCode);
        } catch (Exception e) {
            studentView.displayRegistrationFailure(e.getMessage());
        }
    }
    public void viewMarks(Student student) {
        studentView.displayMarks(student);
    }
}