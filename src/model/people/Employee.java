package model.people;

import enums.SEX;
import java.util.*;
import model.misc.Message;
import org.jetbrains.annotations.NotNull;

public abstract class Employee extends User {
    private static int employeeID = 1;
    private double salary;
    // Inbox: A map of sender -> list of messages from that sender
    private Map<Employee, Message> inbox;

    public Employee(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.salary = salary;
        super.email = name.charAt(0) + "." + surname + "@kbtu.kz";
        this.inbox = new HashMap<>();
        employeeID++;
    }

    public void sendMessages(Employee recipient, String messageText) {
        Message message = new Message(this, messageText);
        recipient.inbox.putIfAbsent(this, message);
    }

    public void viewInbox() {
        if (inbox.isEmpty()) {
            System.out.println("No messages.");
            return;
        }
        for (Map.Entry<Employee, Message> entry : inbox.entrySet()) {
            Employee sender = entry.getKey();
            System.out.println("Message from " + sender.getName() + " " + sender.getSurname() + ": " + entry.getValue().getText());
        }
    }

    public static int getEmployeeID() {
        return employeeID;
    }
    public double getSalary() {
        return salary;
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