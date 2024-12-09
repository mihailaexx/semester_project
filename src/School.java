import java.util.Vector;

public class School {
    private University university;

    private String name;
    private Teacher dean;
    private Vector<Employee> employees; // may have teachers and researchers
    private Vector<Student> students;
    private Vector<Discipline> disciplines;

    public School(String name, University university) {
        this.name = name;
        this.university = university; university.addSchool(this);
        this.employees = new Vector<Employee>();
        this.students = new Vector<Student>();
        this.disciplines = new Vector<Discipline>();
    }

    public void addDean(Teacher dean) {
        if (employees.contains(dean)) {
            this.dean = dean;
        } else {
            System.out.println("Dean must be (firstly) an employee of the school");
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }

    public String getName() {
        return name;
    }

    public Teacher getDean() {
        return dean;
    }
}
