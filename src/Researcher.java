import java.util.Date;
import java.util.Vector;

public class Researcher extends Employee {
    private RESEARCHERDEGREE type;
    private Vector<ResearchPaper> researches;

    public Researcher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, RESEARCHERDEGREE type) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.type = type;
    }
}
enum RESEARCHERDEGREE {
    SENIOR,
    JUNIOR
}