package service;

import data.DataStore;
import model.academic.Course;
import model.academic.Mark;
import model.people.Student;

import java.util.List;

public class StudentService {
    private final DataStore dataStore;

    public StudentService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String getStudentTranscript(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        if (student == null) {
            return "Student not found.";
        }

        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for: ").append(student.getName()).append(" ").append(student.getSurname())
                .append(" (ID: ").append(student.getStudentID()).append(")\n");

        for (Course course : student.getEnrolledCourses()) {
            Mark mark = course.getMarkForStudent(student);
            if (mark != null) {
                transcript.append("Course: ").append(course.getName())
//                        .append(", Mark: ").append(mark.getTotalMark())
                        .append(", Grade: ").append(mark.getStringMark()).append("\n");
            } else {
                transcript.append("Course: ").append(course.getName())
                        .append(", Mark: N/A\n");
            }
        }

        transcript.append("GPA: ").append(student.getGpa());
        return transcript.toString();
    }

    public List<Course> getStudentCourses(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        return (student != null) ? student.getEnrolledCourses() : List.of();
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        dataStore.addStudentToCourse(studentId, courseCode);
    }

//    public List<Mark> getStudentMarks(String studentId) {
//        Student student = dataStore.getStudentById(studentId);
//        // Assuming the Student class has a method to retrieve marks
////        return (student != null) ? student.getMarks() : List.of();
//    }

    public void rateTeacher(String studentId, String teacherId, int rating) {
        // Assuming a method in DataStore to find and rate a teacher
//        dataStore.rateTeacher(studentId, teacherId, rating);
    }
}