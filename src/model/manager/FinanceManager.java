package model.manager;

import enums.SEX;
import model.misc.University;
import model.people.Employee;
import model.people.Person;

import java.util.Date;

import model.people.Student;
import org.jetbrains.annotations.NotNull;

public class FinanceManager extends Employee implements Comparable<Person> {
    public void sync(University university) {
        university.addEmployee(this);
    }

    public FinanceManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void payScholarship(Student student, double amount) {
        double gpa = student.getGpa();
        int totalRetakes = student.getTotalRetakes();

        if (gpa > 3.5 && totalRetakes < 2) {
            // or any other check ( for now gpa > 3.5 && totalRetakes < 2)
            System.out.println("Scholarship of " + amount + " paid to " + student.getName());
        } else {
            System.out.println("Student does not qualify for scholarship.");
        }
    }

    public void paySalary(Employee employee, double amount) {
        System.out.println("Paid " + amount + " salary to " + employee.getName() + " " + employee.getSurname());
        // any other methods.?
    }

    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
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
