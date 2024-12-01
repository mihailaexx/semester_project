import java.util.Date;
import java.util.Vector;

public class Teacher extends Employee {
    private School school;

    private Vector<Discipline> disciplines;
    private TEACHERDEGREE degree;

    public Teacher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, School school, TEACHERDEGREE degree) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.school = school; school.addEmployee(this);
        this.degree = degree;
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }

    public void viewDisciplines() {
        for (Discipline discipline : disciplines) {
            System.out.println(discipline);
        }
    }
}

enum TEACHERDEGREE {
    PROFESSOR,
    ASSOCIATE_PROFESSOR,
    ASSISTANT_PROFESSOR,
    SENIOR_LECTURER,
    LECTURER,
    TUTOR
}