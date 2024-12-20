package data;

import model.academic.Course;
import model.manager.OrManager;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataWrapper implements Serializable {
    private static final long serialVersionUID = 16L;

    private final Map<String, User> users;
    private final Map<String, Student> students;
    private final Map<String, Teacher> teachers;
    private final Map<String, Employee> employees;
    private final Map<String, Course> courses;
    private final Map<String, Researcher> researchers;
    private final Map<Integer, ResearchPaper> researchPapers;
    private final Map<String, OrManager> orManagers;

    public DataWrapper(Map<String, User> users, Map<String, Student> students,
                       Map<String, Teacher> teachers, Map<String, Employee> employees,
                       Map<String, Course> courses, Map<String, Researcher> researchers,
                       Map<Integer, ResearchPaper> researchPapers, Map<String, OrManager> orManagers) {
        this.users = users;
        this.students = students;
        this.teachers = teachers;
        this.employees = employees;
        this.courses = courses;
        this.researchers = researchers;
        this.researchPapers = researchPapers;
        this.orManagers = orManagers;
    }

    // Getters for all the fields

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public Map<String, Teacher> getTeachers() {
        return teachers;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public Map<String, Researcher> getResearchers() {
        return researchers;
    }

    public Map<Integer, ResearchPaper> getResearchPapers() {
        return researchPapers;
    }
    public Map<String, OrManager> getOrManagers() {
        return orManagers;
    }
}