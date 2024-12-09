import java.util.Date;
import enums.SEX;
import org.jetbrains.annotations.NotNull;

public class FinanceManager extends Employee implements Comparable<Person> {
    public void sync(University university) {
        university.addEmployee(this);
    }

    public FinanceManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void payScholarship() {
        // add check for student's grand and n of retakes
    }

    public void paySalary(Employee employee, double amount) {
        // Implementation
    }

    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
