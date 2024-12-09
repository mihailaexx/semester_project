import java.util.Date;
import java.util.Objects;
import enums.SEX;


public abstract class User extends Person {
    String email; // email = login
    private String password;

    public User(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship);
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
        // check if email and password are correct
    }

    @Override
    public int compareTo(Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User[[" + super.toString() +
                "], email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
