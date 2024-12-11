package model.manager;

import enums.SEX;
import enums.STUDENTTYPE;
import model.misc.University;
import model.people.Employee;
import model.people.Person;

import java.util.Date;

import model.people.Student;
import org.jetbrains.annotations.NotNull;

public class FinanceManager extends Employee implements Comparable<Employee> {

    public FinanceManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void payScholarship() {
        for (Student student : University.getStudents()) {
            if (student.getTotalRetakes() < 3 && student.getType() == STUDENTTYPE.GRANT) {
                student.addMoney(47135);
            }
        }
    }

    public void paySalary() {
        for (Employee emp : University.getEmployees()) {
            emp.addMoney(emp.getSalary());
        }
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
        return "FinanceManager[" + super.toString() + ']';
    }
}
