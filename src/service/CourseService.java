package service;

import data.DataStore;
import enums.LESSON_TYPE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.people.Student;
import model.people.Teacher;

import java.time.DayOfWeek;
import java.time.LocalTime;

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

    public void updateCourse(Course updatedCourse) {
        dataStore.saveCourse(updatedCourse);
    }

    public void removeCourse(String courseCode) {
        Course courseToRemove = dataStore.getCourseByCode(courseCode);
        if (courseToRemove != null) {
            dataStore.getAllCourses().remove(courseToRemove);
        } else {
            System.err.println("Failed to remove course: course not found.");
        }
    }

    public void addCourseSession(String courseCode, LESSON_TYPE lessonType, DayOfWeek day, LocalTime time) {
        dataStore.addCourseSession(courseCode, lessonType, day, time);
    }
    public Schedule getCourseSchedule(String courseCode) {
        return dataStore.getCourseSchedule(courseCode);
    }

    public void assignCourseToTeacher(String courseCode, String teacherId) {
        Course course = getCourseByCode(courseCode);
        Teacher teacher = dataStore.getTeacherById(teacherId);

        if (course != null && teacher != null) {
            teacher.addCourse(course);
            course.addInstructor(teacher);
            dataStore.saveCourse(course);
            dataStore.saveTeacher(teacher);
        } else {
            System.err.println("Course or teacher not found.");
        }
    }
    // Add other methods as needed
}