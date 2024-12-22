package service;

import data.DataStore;
import exceptions.CourseNotFoundException;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.manager.Request;
import model.people.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private final DataStore dataStore;
    private OrManagerService orManagerService;

    public StudentService(DataStore dataStore, OrManagerService orManagerService) {
        this.dataStore = dataStore;
        this.orManagerService = orManagerService;
    }
    public Map<Course, Mark> getStudentMarks(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        if (student == null) {
            return new HashMap<>();
        }
        return student.getMarks();
    }
    public String getStudentTranscript(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        if (student == null) {
            return "Student not found.";
        }

        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for: ").append(student.getName()).append(" ").append(student.getSurname())
                .append(" (ID: ").append(student.getStudentID()).append(")\n");

        transcript.append("Enrolled Courses:\n");
        for (Course course : student.getEnrolledCourses()) {
            transcript.append("- ").append(course.getName()).append(" (").append(course.getCode()).append("), Credits: ").append(course.getCredits()).append("\n");
        }

        transcript.append("\nMarks:\n");
        for (Map.Entry<Course, Mark> entry : student.getMarks().entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();
            transcript.append("- ").append(course.getName()).append(": ");
            if (mark != null) {
                transcript.append("Total Mark: ").append(mark.getTotalMark())
                        .append(", Grade: ").append(mark.getStringMark()).append("\n");
            } else {
                transcript.append("N/A\n");
            }
        }

        transcript.append("GPA: ").append(student.getGpa()).append("\n");

        return transcript.toString();
    }

    public List<Course> getStudentCourses(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        return (student != null) ? student.getEnrolledCourses() : List.of();
    }

    public void requestRegistration(String studentId, String courseCode) throws CourseNotFoundException {
        Student student = dataStore.getStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }

        Course course = dataStore.getCourseByCode(courseCode);
        if (course == null) {
            throw new CourseNotFoundException("Course not found: " + courseCode);
        }

        Request request = new Request(student, course);
        dataStore.addRegistrationRequest(request);
    }

    public Schedule getStudentSchedule(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        if (student != null) {
            Schedule schedule = new Schedule();
            for (Course course : student.getEnrolledCourses()) {
                Schedule courseSchedule = course.getSchedule();
                if (courseSchedule != null) {
                    schedule.merge(courseSchedule);
                }
            }
            return schedule;
        }
        return null;
    }

    public void addRating(Student student, String teacherId, int rating, TeacherService teacherService) {
        teacherService.addRating(teacherId, student.getStudentID(), rating);
    }
}