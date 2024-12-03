import java.util.Date;
import java.util.Vector;

/**
 * Researcher class
 * @param <T> - User
 */
public class Researcher<T> extends Employee {
    private T user;
    private RESEARCHERDEGREE type;
    private Vector<ResearchPaper> researches;
    private Vector<ResearchProject> projects;
    private int hIndex;

    public void sync(University university) {
        university.addEmployee(this);
    }

    public Researcher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, RESEARCHERDEGREE type) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.type = type;
    }


}
enum RESEARCHERDEGREE {
    SENIOR,
    JUNIOR
}