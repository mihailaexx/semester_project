import java.util.Date;

public class Government extends Employee {
    private GOVROLE role;

    public void sync(University university) {
        university.addEmployee(this);
    }

    public Government(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, GOVROLE role) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.role = role;
    }

    public void addEmployee(Employee employee) {
        // add employee to the university
    }

    public void removeEmployee(Employee employee) {
        // remove employee from the university
    }

    public void updateEmployee(Employee employee) {
        // update employee in the university
    }

    public void addDiscipline(Discipline discipline) {
        // add discipline to the university
    }

    public void removeDiscipline(Discipline discipline) {
        // remove discipline from the university
    }

    public void updateDiscipline(Discipline discipline) {
        // update discipline in the university
    }

    public void addResearcher(Researcher researcher) {
        // add researcher to the university
    }

    public void removeResearcher(Researcher researcher) {
        // remove researcher from the university
    }

    public void updateResearcher(Researcher researcher) {
        // update researcher in the university
    }

    public void approveCourse(Student student, Course course) {
        // approve course
    }

    public void viewRequests() {
        // view requests
    }

    public void createReport() {
        // create report
    }

    public void seeLogs() {
        // see logs
    }
}

enum GOVROLE {
    RECTOR,
    VICE_RECTOR,
    MANAGER,
    FINANCE_MANAGER,
    HR_MANAGER,
    SECRETARY,
    OR
}