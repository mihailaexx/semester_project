package model.people;

import enums.SEX;

import java.io.Serializable;
import java.util.*;

public abstract class Employee extends User implements Serializable {
    private static final long serialVersionUID = 3L;

    private String employeeId;
    private double salary;
    private Date hireDate;
    private Map<User, String> messages;

    public Employee(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
        super(name, surname, sex, birthDate, email, email, password, phoneNumber, citizenship);
        this.employeeId = generateEmployeeId();
        this.salary = salary;
        this.hireDate = new Date();
        this.messages = new HashMap<>();
    }


    public String getEmployeeId() { return employeeId;}
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public Date getHireDate() {return hireDate; }


    private String generateEmployeeId() {
        String prefix = (this instanceof Teacher) ? "T" : "E"; // Differentiate between Teacher and other Employee types
        String uniqueDigits = String.format("%05d", new Random().nextInt(100000));
        return prefix + uniqueDigits;
    }

    public void sendMessage(User recipient, String message) { messages.put(recipient, message); }

    public void viewMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }
        for (Map.Entry<User, String> entry : messages.entrySet()) {
            User sender = entry.getKey();
            String message = entry.getValue();
            System.out.println("Message from " + sender.getUsername() + ": " + message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                "} " + super.toString();
    }

}