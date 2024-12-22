package model.manager;

import enums.SEX;
import exceptions.CourseRegistrationException;
import model.people.*;

import java.io.Serializable;
import java.util.*;

public class OrManager extends Employee implements Serializable {
    private static final long serialVersionUID = 8L;

    private List<Request> requests;


    public OrManager(String name, String surname, SEX sex, Date birthDate, String email, String password, String phoneNumber, String citizenship, double salary) {
        super(name, surname, sex, birthDate, email, password, phoneNumber, citizenship, salary);
    }

    public void addRequest(Request request) throws CourseRegistrationException {
        this.requests.add(request);
    }

    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrManager)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "OrManager{" +
                "employeeId=" + getEmployeeId() +
                ", salary=" + getSalary() +
                ", hireDate=" + getHireDate() +
                "} " + super.toString();
    }
}