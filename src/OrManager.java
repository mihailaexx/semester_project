import java.util.Date;
import enums.SEX;
import org.jetbrains.annotations.NotNull;

public class OrManager extends Employee implements Comparable<Person> {
    public void sync(University university) {
        university.addEmployee(this);
    }

    public OrManager(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
    }

    public void seeRequests() {
        //
    }

    public void approveRequest(Student student) {
        //
    }

    public void addDisciplineForRegistration(Discipline discipline, String major, int year) {
        //
    }

    public void assignDisciplineToTeacher(Discipline discipline, Teacher teacher) {
        teacher.addDiscipline(discipline);
    }

    public void removeDiscipline(Discipline discipline) {
        //
    }

    public void updateDiscipline(Discipline discipline_to_edit, Discipline new_discipline) {
        //
    }

    public void createStatisticalReport() {
        // Implementation
    }

    public void manageNews(String news) {
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
