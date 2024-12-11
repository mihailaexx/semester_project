package model.people;

import enums.SEX;
import model.misc.University;

import java.util.Date;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Admin extends Employee implements Comparable<Admin> {
    private int privilegeLevel;

    public Admin(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, int PrivilegeLevel) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.privilegeLevel = PrivilegeLevel;
    }

    public void addUser(User user) {}
    public void removeUser(User user) {}
    public void updateUser(User user) {}
    public void seeLogs() {}

    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    @Override
    public int compareTo(@NotNull Admin o) {
        return Integer.compare(this.privilegeLevel, o.privilegeLevel);
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
