import java.util.Date;

public abstract class User extends Person {
    String email; // email = login
    private String password;

    public User(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship);
        // TODO: email = name.substring(0, 1) + "_ or ." + surname + "@kbtu.kz", implement this in Student and Employee constructors
        this.password = password;
    }

//    public abstract boolean login();
}
