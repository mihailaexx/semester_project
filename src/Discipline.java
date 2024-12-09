import java.util.Vector;

public class Discipline {
    private String code;
    private String name;
    private School school;
    private int credits;
    private int n_lectures;
    private int n_practices;
    private int n_labs;
    private int year;
    private int semester;

    public void sync(University university) {
        university.addDiscipline(this);
    }

    public Discipline() {}
    public Discipline(String code, String name, School school, int credits, int n_lectures, int n_practices, int n_labs, int year, int semester) {
        this.code = code;
        this.name = name;
        this.school = school; school.addDiscipline(this);
        this.credits = credits;
        this.n_lectures = n_lectures;
        this.n_practices = n_practices;
        this.n_labs = n_labs;
        this.year = year;
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public School getSchool() {
        return school;
    }

    public int getCredits() {
        return credits;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public String getFormula() {
        return String.format("%d/%d/%d", n_lectures, n_practices, n_labs);
    }
}