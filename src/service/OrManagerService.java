package service;

import data.DataStore;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrManagerService {
    private final DataStore dataStore;

    public OrManagerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // in future add method to make schedule for student, teacher
    public void createStatisticalReport() { }
    public void viewStudentsByGPA() { }
    public void viewTeachersAlphabetically() { }
    public void viewUsers() {}
    public void updateUser() {}
    public void addNewCourse() {}
    public void updateCourse() {}
    public void removeCourse() {}
    public void viewCourse() {}
    public void addCourseSession() {}
    public void assignCourseToTeacher() {}
    public void approveRegistrationRequest() {}
    public void rejectRegistrationRequest() {}
    public void viewCourseSchedule() {}
    public void viewStudentSchedule() {}
    public void viewTeacherSchedule() {}
}