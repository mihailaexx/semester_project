import java.util.Vector;

public class University {
    private String name;
    private Government rector;
    private Vector<Admin> admins;
    private Vector<Teacher> deans;
    private Vector<Employee> employees;
    private Vector<Student> students;
    private Vector<Discipline> disciplines;

    private Vector<School> schools;

    private Vector<ResearchPaper> allResearchPapers;
    private Vector<String> log; // or txt file

    public University(String name) {
        this.name = name;
        this.admins = new Vector<Admin>();
        this.deans = new Vector<Teacher>();
        this.employees = new Vector<Employee>();
        this.students = new Vector<Student>();
        this.disciplines = new Vector<Discipline>();
        this.schools = new Vector<School>();
        this.allResearchPapers = new Vector<ResearchPaper>();
        this.log = new Vector<String>();
    }

    public void addRector(Government rector) {
        this.rector = rector;
        employees.add(rector);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
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

    public void addSchool(School school) {
        schools.add(school);
        deans.add(school.getDean());
    }

    public void addResearchPaper(ResearchPaper researchPaper) {
        allResearchPapers.add(researchPaper);
    }

    public void addLog(String log) {
        this.log.add(log);
    }
}
