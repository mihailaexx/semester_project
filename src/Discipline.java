public class Discipline {
    private String code;
    private String name;
    private School school;
    private int credits;
    private DisciplineFormula formula;
    private int year;
    private int semester;
    private Teacher[] teacher;

    public Discipline(String code, String name, School school, int credits, DisciplineFormula formula, int year, int semester, Teacher[] teacher) {
        this.code = code;
        this.name = name;
        this.school = school;
        this.credits = credits;
        this.formula = formula;
        this.year = year;
        this.semester = semester;
        this.teacher = teacher;
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

    public String getFormula() {
        return this.formula.toString();
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }
}

enum DISCIPLINETYPE {
    LECTURE,
    PRACTICE,
    LAB
}