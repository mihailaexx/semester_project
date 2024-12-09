package model.manager;

import java.util.Date;
import enums.SEX;
import model.academic.Discipline;
import model.misc.University;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;

public class OrManager extends Employee {
    @Override
    public void sync(University university) {
        university.addEmployee(this);
    }

    public OrManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void seeRequests() {
        //
    }

    public void approveRequest(Student student) {
        //
    }

    public void addDisciplineForRegistration(Discipline discipline, String major, int year) {
        //
    }

    public void assignDisciplineToTeacher(Discipline discipline, Teacher teacher) {
        teacher.addDiscipline(discipline);
    }

    public void removeDiscipline(Discipline discipline) {
        //
    }

    public void updateDiscipline(Discipline discipline_to_edit, Discipline new_discipline) {
        //
    }

    public void createStatisticalReport() {
        // Implementation
    }

    public void manageNews(String news) {
        // Implementation
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
        return super.toString();
    }
}
