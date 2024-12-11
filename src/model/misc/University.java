package model.misc;

import model.academic.Course;
import model.people.Admin;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.research.ResearchPaper;

import java.util.Objects;
import java.util.Vector;

public class University {
    private String name;

    private static Vector<School> schools;
    private static Vector<Admin> admins;
    private static Vector<Teacher> deans;
    private static Vector<Employee> employees;
    private static Vector<Student> students;
    private static Vector<Course> courses;
    private static Vector<ResearchPaper> allResearchPapers;
    private Vector<String> log; // or txt file

    public University(String name) {
        this.name = name;
        schools = new Vector<School>();
        admins = new Vector<Admin>();
        deans = new Vector<Teacher>();
        employees = new Vector<Employee>();
        students = new Vector<Student>();
        courses = new Vector<Course>();
        allResearchPapers = new Vector<ResearchPaper>();
        this.log = new Vector<String>();
    }

    // Add methods for searching/filtering, e.g., find a Student by ID, find a Course by code, etc.
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


    public Student findStudentById(String ID) {
        for (Student s : students) {
            if (s.getID().equals(ID)) return s;
        }
        return null;
    }

    public Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCode().equals(code)) return c;
        }
        return null;
    }

    public String getName() {
        return name;
    }
    public Vector<School> getSchools() {
        return schools;
    }
    public static Vector<Admin> getAdmins() {
        return admins;
    }
    public static Vector<Teacher> getDeans() {
        return deans;
    }
    public static Vector<Employee> getEmployees() {
        return employees;
    }
    public static Vector<Student> getStudents() {
        return students;
    }
    public static Vector<Course> getCourses() {
        return courses;
    }
    public Vector<Teacher> getTeachers() {
        Vector<Teacher> result = new Vector<>();
        for (Employee e : employees) {
            if (e instanceof Teacher t) result.add(t);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof University that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), log);
    }

    @Override
    public String toString() {
        return "University[" +
                "name='" + name + "']";
    }
}
