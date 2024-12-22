package controller;

import exceptions.CourseNotFoundException;
import model.academic.Schedule;
import model.people.Student;
import service.CourseService;
import service.StudentService;
import service.TeacherService;
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
        String transcript = studentService.getStudentTranscript(student.getStudentID());
        studentView.displayTranscript(transcript);
    }

    public void viewStudentCourses(Student student) {
        studentView.displayCourses(student.getEnrolledCourses());
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        try {
            studentService.requestRegistration(student.getStudentID(), courseCode);
            studentView.displayRegistrationSuccess(courseCode);
        } catch (CourseNotFoundException e) {
            studentView.displayRegistrationFailure(e.getMessage());
        }
    }
    public void viewMarks(Student student) {
        studentView.displayMarks(student);
    }

    public void viewStudentSchedule(Student student) {
        Schedule schedule = studentService.getStudentSchedule(student.getStudentID());
        if (schedule != null) {
            studentView.displaySchedule(schedule);
        } else {
            studentView.displayErrorMessage("Could not retrieve schedule for student " + student.getStudentID());
        }
    }

    public void addRating(Student student, String teacherId, int rating, TeacherService teacherService) {
        teacherService.addRating(teacherId, student.getStudentID(), rating);
    }
}