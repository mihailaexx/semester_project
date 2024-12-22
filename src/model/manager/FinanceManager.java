package model.manager;

import enums.SEX;
import model.people.Employee;
import model.people.Student;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FinanceManager extends Employee implements Serializable {
    private static final long serialVersionUID = 7L;


    public FinanceManager(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
    }

    public void payStipend(Student student) {
        if (!canReceiveStipend(student)) {
            System.out.println("Student " + student.getName() + " " + student.getSurname() + " is not eligible for a stipend.");
            return;
        }

        double stipendAmount = calculateStipend(student);
        System.out.println("Stipend of $" + stipendAmount + " paid to student " + student.getName() + " " + student.getSurname());
    }

    public void paySalary(Employee employee) {
        double salaryAmount = employee.getSalary();
        System.out.println("Salary of $" + salaryAmount + " paid to employee " + employee.getName() + " " + employee.getSurname());
    }

    private boolean canReceiveStipend(Student student) {
        return student.getGpa() >= 3.0;
    }

    private double calculateStipend(Student student) {
        if (student.getGpa() >= 3.67) return 1000.0;
        else if (student.getGpa() >= 3.5) return 800.0;
        else if (student.getGpa() >= 3.0) return 600.0;
        else return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinanceManager)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "FinanceManager{" +
                "employeeId=" + getEmployeeId() +
                ", salary=" + getSalary() +
                ", hireDate=" + getHireDate() +
                "} " + super.toString();
    }
}