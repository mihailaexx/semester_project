package model.people;

import enums.SEX;

import java.util.Date;

public class Dean extends Employee {
    public Dean(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void receiveComplaint(Teacher teacher, String complaint, String urgencyLevel) {
        System.out.println("Dean received complaint: " + complaint + " with urgency " + urgencyLevel);
    }
}
