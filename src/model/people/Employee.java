package model.people;

import enums.SEX;

import java.util.Date;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public abstract class Employee extends User implements Comparable<Person> {
    private static int employeeID = 1;
    private double salary;

    public Employee(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.salary = salary;
        super.email = name.charAt(0) + "." + surname + "@kbtu.kz";
        employeeID++;
    }

    public void sendMessages(Employee employee, String message) {}

    public static int getEmployeeID() {
        return employeeID;
    }
    public double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getSalary(), employee.getSalary()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSalary());
    }

    @Override
    public String toString() {
        return "Employee[[" + super.toString() +
                "], salary=" + salary +
                ']';
    }
}