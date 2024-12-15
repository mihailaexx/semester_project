package controller;

import data.DataStore;
import model.people.Student;
import model.people.Teacher;
import service.CourseService;
import service.TeacherService;
import view.TeacherView;
import model.academic.Course;

import java.util.List;

public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final TeacherView teacherView;

    public TeacherController(TeacherService teacherService, CourseService courseService, TeacherView teacherView) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.teacherView = teacherView;
    }

    public void viewTeacherCourses(Teacher teacher) {
        teacherView.displayCourses(teacher.getCourses());
    }
    public void viewStudentsInCourse(Teacher teacher, String courseCode, DataStore dataStore) {
        teacherView.displayStudents(teacher, courseCode, dataStore);
    }
    public void addMark(Teacher teacher, String courseCode, String studentId, double att1, double att2, double finalExam) {
        courseService.addMark(teacher, courseCode, studentId, att1, att2, finalExam);
    }
//    public void sendComplaint(Teacher teacher, String studentId, String message, String urgency) {
//        teacherService.sendComplaint(teacher, studentId, message, urgency);
//    }
}