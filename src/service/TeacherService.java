package service;

import data.DataStore;
import model.academic.Course;
import model.academic.Mark;
import model.people.Student;
import model.people.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {
    private final DataStore dataStore;

    public TeacherService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Course> getTeacherCourses(int teacherId) {
        Teacher teacher = dataStore.getTeacherById(teacherId);
        return (teacher != null) ? teacher.getCourses() : List.of();
    }

    public List<Student> getStudentsInCourse(int teacherId, String courseCode) {
        Teacher teacher = dataStore.getTeacherById(teacherId);
        Course course = dataStore.getCourseByCode(courseCode);
        if (teacher == null || course == null || !teacher.getCourses().contains(course)) {
            return List.of();
        }

        return dataStore.getAllStudents().stream()
                .filter(student -> student.getCourses().contains(course))
                .collect(Collectors.toList());
    }

    public void addMark(int teacherId, String courseCode, String studentId, double att1, double att2, double finalExam) {
        Teacher teacher = dataStore.getTeacherById(teacherId);
        Course course = dataStore.getCourseByCode(courseCode);
        Student student = dataStore.getStudentById(studentId);

        if (teacher == null || course == null || student == null || !teacher.getCourses().contains(course)) {
            System.err.println("Invalid teacher, course, or student ID.");
            return;
        }

        Mark mark = new Mark(att1, att2, finalExam);
        dataStore.addMarkToStudent(studentId, courseCode, mark);
    }

    public void sendComplaint(int teacherId, String studentId, String message, String urgency) {
        Teacher teacher = dataStore.getTeacherById(teacherId);
        Student student = dataStore.getStudentById(studentId);

        if (teacher == null || student == null) {
            System.err.println("Invalid teacher or student ID.");
            return;
        }
    }
}