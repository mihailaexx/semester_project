package model.manager;

import enums.SEX;
import model.academic.Course;
import model.misc.University;
import model.people.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class OrManager extends Employee implements Comparable<Employee> {

    public OrManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void seeRequests() {
    }

    public void approveRequest() {
    }

    public void createStatisticalReport(University university) {
        System.out.println("Statistical Report:");
        System.out.println("Total Students: " + university.getStudents().size());
        System.out.println("Total Employees: " + university.getEmployees().size());
    }

    public void manageNews(University university, String news) {
        university.addLog("NEWS: " + news);
    }

    public void viewStudentsByGPA(University university) {
        List<Student> students = new ArrayList<>(university.getStudents());
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("Students sorted by GPA:");
        for (Student s : students) System.out.println(s.getName() + " " + s.getSurname() + ": " + s.getGpa());
    }

    public void viewTeachersAlphabetically(University university) {
        List<Teacher> teachers = new ArrayList<>(university.getTeachers());
        teachers.sort(Comparator.comparing(Teacher::getSurname));
        System.out.println("Teachers sorted alphabetically by surname:");
        for (Teacher t : teachers) System.out.println(t.getSurname() + ", " + t.getName());
    }
    @Override
    public int compareTo(@NotNull Employee o) {
        return Double.compare(this.getSalary(), o.getSalary());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "OrManager[" + super.toString() + ']';
    }
}
