package model.misc;

import model.academic.Course;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;

import java.util.Objects;
import java.util.Vector;

public class School {
    private University university;

    private String name;
    private Teacher dean;
    private static Vector<Employee> employees; // may have teachers and researchers
    private static Vector<Student> students;
    private static Vector<Course> courses;

    public School(String name, University university) {
        this.name = name;
        this.university = university; university.addSchool(this);
        employees = new Vector<Employee>();
        students = new Vector<Student>();
        courses = new Vector<Course>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void addDiscipline(Course course) {
        courses.add(course);
    }
    public void addDean(Teacher dean) {
        if (employees.contains(dean)) {
            this.dean = dean;
        } else {
            System.out.println("Dean must be (firstly) an employee of the school");
        }
    }

    public String getName() {
        return name;
    }
    public Teacher getDean() {
        return dean;
    }
    public Vector<Employee> getEmployees() {
        return employees;
    }
    public Vector<Student> getStudents() {
        return students;
    }
    public Vector<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof School school)) return false;
        return Objects.equals(university, school.university) && Objects.equals(getName(), school.getName()) && Objects.equals(getDean(), school.getDean());
    }

    @Override
    public int hashCode() {
        return Objects.hash(university, getName(), getDean());
    }

    @Override
    public String toString() {
        return "School[" +
                "university=" + university +
                ", name='" + name + '\'' +
                ", dean=" + dean +
                ", employees=" + employees +
                ", students=" + students +
                ", courses=" + courses +
                ']';
    }
}
