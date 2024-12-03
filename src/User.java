import java.util.Date;
import java.util.Objects;

public abstract class User extends Person {
    String email; // email = login
    private String password;

    public User(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship);
        this.password = password;
    }



    @Override
    public int compareTo(Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }

    @Override
    public String toString() {
        return "User[" + super.toString() +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
