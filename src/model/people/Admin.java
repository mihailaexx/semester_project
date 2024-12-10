package model.people;

import enums.SEX;
import model.misc.University;

import java.util.Date;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Admin extends Employee implements Comparable<Person> {
    private int privilegeLevel;

    public void sync(University university) {
        university.addAdmin(this);
    }

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

//    public void addDiscipline(Discipline discipline) {}
//    public void removeDiscipline(Discipline discipline) {}
//    public void updateDiscipline(Discipline discipline) {}
// should be managed by orManagers

    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
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
