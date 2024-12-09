package model.people;

import java.util.Date;
import java.util.Objects;
import enums.SEX;
import model.misc.University;


public class Admin extends Employee {
    private int privilegeLevel;

    public Admin(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, int PrivilegeLevel) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.privilegeLevel = PrivilegeLevel;
    }

    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void addUser(User user) {}
    public void removeUser(User user) {}
    public void updateUser(User user) {}
    public void seeLogs() {}

//    public void addDiscipline(model.academic.Discipline discipline) {}
//    public void removeDiscipline(model.academic.Discipline discipline) {}
//    public void updateDiscipline(model.academic.Discipline discipline) {}
// should be managed by orManagers

    @Override
    public void sync(University university) {
        university.addAdmin(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return getPrivilegeLevel() == admin.getPrivilegeLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrivilegeLevel());
    }

    @Override
    public String toString() {
        return "Admin[[" + super.toString() +
                "], privilegeLevel=" + privilegeLevel +
                ']';
    }
}
