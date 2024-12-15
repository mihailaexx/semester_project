package service;

import data.DataStore;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.people.Student;
import model.people.Teacher;
import model.academic.Mark;

public class CourseService {
    private final DataStore dataStore;

    public CourseService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addCourse(Course course) {
        dataStore.saveCourse(course);
    }

    public Course getCourseByCode(String courseCode) {
        return dataStore.getCourseByCode(courseCode);
    }

    public void registerStudentForCourse(Student student, String courseCode) throws CourseRegistrationException {
        Course course = dataStore.getCourseByCode(courseCode);
        if (course != null) {
            student.registerForCourse(course);
            dataStore.saveStudent(student);
        } else {
            throw new CourseRegistrationException("Course not found: " + courseCode);
        }
    }
    public void addMark(Teacher teacher, String courseCode, String studentId, double att1, double att2, double finalExam) {
        Course course = dataStore.getCourseByCode(courseCode);
        Student student = dataStore.getStudentById(studentId);

        if (course != null && student != null && teacher.getCourses().contains(course)) {
            Mark mark = new Mark(att1, att2, finalExam);
            dataStore.addMarkToStudent(studentId, courseCode, mark);
        } else {
            System.err.println("Failed to add mark: invalid course, student, or teacher not assigned to course.");
        }
    }
}