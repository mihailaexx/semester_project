package controller;

import data.DataStore;
import exceptions.InvalidMarkException;
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
    private final DataStore dataStore;

    public TeacherController(TeacherService teacherService, CourseService courseService, TeacherView teacherView, DataStore dataStore) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.teacherView = teacherView;
        this.dataStore = dataStore;
    }

    public void viewTeacherCourses(Teacher teacher) {
        List<Course> courses = teacherService.getTeacherCourses(teacher.getEmployeeId());
        teacherView.displayCourses(courses);
    }

    public void viewStudentsInCourse(Teacher teacher, String courseCode, DataStore dataStore) {
        teacherView.displayStudents(teacher, courseCode, dataStore);
    }

    public void addMark(Teacher teacher, String courseCode, String studentId, double att1, double att2, double finalExam) {
        try {
            teacherService.addMark(teacher.getEmployeeId(), courseCode, studentId, att1, att2, finalExam);
            teacherView.displayMarkInputSuccess();
        } catch (Exception e) {
            teacherView.displayMarkInputFailure(e.getMessage());
        }
    }

    public void sendComplaint(Teacher teacher, String studentId, String message, String urgency) {
        try {
            teacherService.sendComplaint(teacher.getEmployeeId(), studentId, message, urgency);
            teacherView.displayComplaintSent();
        } catch (Exception e) {
            teacherView.displayComplaintFailure(e.getMessage());
        }
    }

    // Placeholder method for getting the current teacher
    private Teacher getCurrentTeacher() {
        return dataStore.getAllTeachers().stream().findFirst().orElse(null);
    }
}