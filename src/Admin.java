import java.util.Date;

public class Admin extends User {
    private int PrivilegeLevel;
    public Admin(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, int PrivilegeLevel) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.PrivilegeLevel = PrivilegeLevel;
    }

    public void addUser(User user) {};
    public void removeUser(User user) {};
    public void updateUser(User user) {};
    public void addDiscipline(Discipline discipline) {};
    public void removeDiscipline(Discipline discipline) {};
    public void updateDiscipline(Discipline discipline) {};
    public void addStudentToSchool(Student student) {};
}
