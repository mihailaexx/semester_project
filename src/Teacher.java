import java.util.Date;
import java.util.Vector;

public class Teacher extends Employee {
    private School school;

    private Vector<Discipline> disciplines;
    private TEACHERDEGREE degree;

    public void sync(University university) {
        university.addEmployee(this);
    }

    public Teacher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, School school, TEACHERDEGREE degree) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.school = school; school.addEmployee(this); // school.getUniversity().addEmployee(this);
        this.disciplines = new Vector<Discipline>();
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

    public void updateDiscipline(Discipline discipline_to_edit, Discipline new_discipline) {
        // O(n)
        for (Discipline d : disciplines) {
            if (d.equals(discipline_to_edit)) {
                d = new_discipline;
            }
        }
    }

    public void updateMark(Student student, Discipline discipline, double mark, int i) {
        student.getCourses().lastElement().updateDisciplineMark(discipline, mark, i);
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