package model.people;

import enums.SEX;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Admin extends Employee implements Serializable {
    private static final long serialVersionUID = 6L;

    public Admin(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
    }

    // Methods specific to Admin

    public void addUser(User user) {
        // Add user to your in-memory data store or database
        // You might delegate this to a UserService (e.g., dataStore.saveUser(user))
        System.out.println("User added: " + user.getUsername());
    }

    public void removeUser(User user) {
        // Remove user from data store
        System.out.println("User removed: " + user.getUsername());
    }

    public void updateUser(User user) {
        // Update user details in data store
        System.out.println("User updated: " + user.getUsername());
    }

    public void viewLogs() {
        // Display system logs (if you are implementing logging)
        System.out.println("Viewing system logs...");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "employeeId=" + getEmployeeId() +
                ", salary=" + getSalary() +
                ", hireDate=" + getHireDate() +
                "} " + super.toString();
    }
}