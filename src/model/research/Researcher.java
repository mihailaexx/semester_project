package model.research;

import enums.SEX;
import enums.RESEARCHERDEGREE;
import model.people.*;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Researcher extends Employee implements Comparable<Researcher> {
    private User user;
    private RESEARCHERDEGREE type;
    private Vector<ResearchPaper> researches;
    private Vector<ResearchProject> projects;
    private int hIndex;

    public Researcher(@NotNull Student student, double salary, RESEARCHERDEGREE type) {
        super(student.getID(), student.getName(), student.getSurname(), student.getSex(), student.getBirthDate(), student.getPhoneNumber(), student.getCitizenship(), student.getPassword(), salary);
        this.type = type;
        this.researches = new Vector<ResearchPaper>();
        this.projects = new Vector<ResearchProject>();
        this.hIndex = 0;
    }

    public Researcher(@NotNull Employee employee, RESEARCHERDEGREE type) {
        super(employee.getID(), employee.getName(), employee.getSurname(), employee.getSex(), employee.getBirthDate(), employee.getPhoneNumber(), employee.getCitizenship(), employee.getPassword(), employee.getSalary());
        this.type = type;
        this.researches = new Vector<ResearchPaper>();
        this.projects = new Vector<ResearchProject>();
        this.hIndex = 0;
    }

    public Researcher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, RESEARCHERDEGREE type) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.type = type;
    }

    @Override
    public int compareTo(@NotNull Researcher o) {
        return Integer.compare(this.hIndex, o.hIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Researcher that)) return false;
        if (!super.equals(o)) return false;
        return hIndex == that.hIndex && Objects.equals(user, that.user) && type == that.type && Objects.equals(researches, that.researches) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, type, researches, projects, hIndex);
    }

    @Override
    public String toString() {
        return "Researcher[[" + super.toString() +
                "], type=" + type +
                ", researches=" + researches +
                ", projects=" + projects +
                ", hIndex=" + hIndex +
                '}';
    }
}
