package data;

import model.academic.Course;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.io.Serializable;
import java.util.Map;

public class DataWrapper implements Serializable {
    private static final long serialVersionUID = 16L;

    private final Map<String, User> users;
    private final Map<String, Student> students;
    private final Map<Integer, Teacher> teachers;
    private final Map<Integer, Employee> employees;
    private final Map<String, Course> courses;
    private final Map<Integer, Researcher> researchers;
    private final Map<Integer, ResearchPaper> researchPapers;

    public DataWrapper(Map<String, User> users, Map<String, Student> students,
                       Map<Integer, Teacher> teachers, Map<Integer, Employee> employees,
                       Map<String, Course> courses, Map<Integer, Researcher> researchers,
                       Map<Integer, ResearchPaper> researchPapers) {
        this.users = users;
        this.students = students;
        this.teachers = teachers;
        this.employees = employees;
        this.courses = courses;
        this.researchers = researchers;
        this.researchPapers = researchPapers;
    }

    // Getters for all the fields

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public Map<Integer, Teacher> getTeachers() {
        return teachers;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public Map<Integer, Researcher> getResearchers() {
        return researchers;
    }

    public Map<Integer, ResearchPaper> getResearchPapers() {
        return researchPapers;
    }
}