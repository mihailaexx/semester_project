//package model.people;
//
//import enums.SEX;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Objects;
//
//public class Admin extends Employee implements Serializable {
//    private static final long serialVersionUID = 6L;
//
//    public Admin(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
//        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
//    }
//
//
//    public void addUser(User user) {
//        System.out.println("User added: " + user.getUsername());
//    }
//
//    public void removeUser(User user) {
//        System.out.println("User removed: " + user.getUsername());
//    }
//
//    public void updateUser(User user) {
//        System.out.println("User updated: " + user.getUsername());
//    }
//
//    public void viewLogs() {
//        System.out.println("Viewing system logs...");
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Admin)) return false;
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode());
//    }
//
//    @Override
//    public String toString() {
//        return "Admin{" +
//                "employeeId=" + getEmployeeId() +
//                ", salary=" + getSalary() +
//                ", hireDate=" + getHireDate() +
//                "} " + super.toString();
//    }
//}