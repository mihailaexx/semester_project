package model.manager;

import model.academic.Course;
import model.people.Student;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 9L;

    private static int nextRequestId = 1;

    private int requestId;
    private Student student;
    private Course course;
    private String status; // e.g., "PENDING", "APPROVED", "REJECTED"

    public Request(Student student, Course course) {
        this.requestId = generateRequestId();
        this.student = student;
        this.course = course;
        this.status = "PENDING";
    }

    private int generateRequestId() {
        return nextRequestId++;
    }
    public int getRequestId() {
        return requestId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", student=" + student +
                ", course=" + course +
                ", status='" + status + '\'' +
                '}';
    }
}