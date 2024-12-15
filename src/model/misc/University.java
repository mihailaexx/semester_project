package model.misc;

import model.academic.Course;
import model.people.Admin;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.research.ResearchPaper;

import java.util.Vector;

public class University {
    private String name;
    private Vector<Admin> admins;
    private Vector<Teacher> deans;
    private Vector<Employee> employees;
    private Vector<Student> students;
    private Vector<Course> courses;

    private Vector<School> schools;

    private Vector<ResearchPaper> allResearchPapers;
    private Vector<String> log; // or txt file

    public University(String name) {
        this.name = name;
        this.admins = new Vector<Admin>();
        this.deans = new Vector<Teacher>();
        this.employees = new Vector<Employee>();
        this.students = new Vector<Student>();
        this.courses = new Vector<Course>();
        this.schools = new Vector<School>();
        this.allResearchPapers = new Vector<ResearchPaper>();
        this.log = new Vector<String>();
    }
    public Vector<School> getSchools() { return new Vector<>(schools); }
    public Vector<Student> getStudents() { return new Vector<>(students); }
    public Vector<Employee> getEmployees() { return new Vector<>(employees); }
    public Vector<Course> getAllCourses() { return new Vector<>(courses); }
    public Vector<Teacher> getTeachers() {
        Vector<Teacher> result = new Vector<>();
        for (Employee e : employees) {
            if (e instanceof Teacher t) result.add(t);
        }
        return result;
    }

//    public Student findStudentById(String ID) {
//        for (Student s : students) {
//            if (s.getID().equals(ID)) return s;
//        }
//        return null;
//    }

    public Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCode().equals(code)) return c;
        }
        return null;
    }

    public String getName() {
        return name;
    }
    // Add methods for searching/filtering, e.g., find a Student by ID, find a Course by code, etc.?
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addSchool(School school) {
        schools.add(school);
        deans.add(school.getDean());
    }

    public void addResearchPaper(ResearchPaper researchPaper) {
        allResearchPapers.add(researchPaper);
    }

    public void addLog(String log) {
        this.log.add(log);
    }
}
