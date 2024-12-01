import java.util.Date;

public class Government extends Employee {
    private GOVROLE role;

    public Government(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, GOVROLE role) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.role = role;
    }
}

enum GOVROLE {
    RECTOR,
    VICE_RECTOR,
    MANAGER,
    FINANCE_MANAGER,
    HR_MANAGER,
    SECRETARY
}