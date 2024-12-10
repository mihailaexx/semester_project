package model.research;

import enums.CITATIONFORMAT;
import model.misc.University;
import model.people.Employee;
import model.people.Student;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import enums.SEX;
import enums.RESEARCHERDEGREE;

public class Researcher extends Employee implements ResearcherInterface{
    private RESEARCHERDEGREE type;
    private Vector<ResearchPaper> researches;
    private Vector<ResearchProject> projects;

    public void sync(University university) {
        university.addEmployee(this);
    }

    public Researcher(@NotNull Student student, double salary, RESEARCHERDEGREE type) {
        super(student.getID(), student.getName(), student.getSurname(), student.getSex(), student.getBirthDate(), student.getPhoneNumber(), student.getCitizenship(), student.getPassword(), salary);
        this.type = type;
        this.researches = new Vector<ResearchPaper>();
        this.projects = new Vector<ResearchProject>();
    }

    public Researcher(@NotNull Employee employee, RESEARCHERDEGREE type) {
        super(employee.getID(), employee.getName(), employee.getSurname(), employee.getSex(), employee.getBirthDate(), employee.getPhoneNumber(), employee.getCitizenship(), employee.getPassword(), employee.getSalary());
        this.type = type;
        this.researches = new Vector<ResearchPaper>();
        this.projects = new Vector<ResearchProject>();
    }

    public Researcher(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary, RESEARCHERDEGREE type) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password, salary);
        this.type = type;
    }

    public void addPaper(ResearchPaper paper) {
        researches.add(paper);
    }

    public void addProject(ResearchProject project) {
        projects.add(project);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        Vector<ResearchPaper> sorted = new Vector<>(researches);
        sorted.sort(c);
        for (ResearchPaper rp : sorted) {
            System.out.println(rp);
        }
    }

    @Override
    public int calculateHIndex() {
        // Sort papers by citations descending
        Vector<ResearchPaper> sorted = new Vector<>(researches);
        sorted.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));

        int h = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getCitations() >= i+1) {
                h = i+1;
            } else {
                break;
            }
        }
        return h;
    }

    @Override
    public String getAllCitations(CITATIONFORMAT format) {
        StringBuilder sb = new StringBuilder();
        for (ResearchPaper rp : researches) {
            sb.append(rp.getCitation(format)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void manageResearchProject(ResearchProject project) {
        // just adding the project to the researcher's list
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    @Override
    public void announcePublishedPaper(ResearchPaper paper) {
        // future implementation: notify the system/news feed
        // For now, just print a message
        System.out.println("Announcement: Researcher " + getSurname() + " has published a paper: " + paper.getTitle());
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researches;
    }

    /**
     * Prints all research papers of all research in the given list, sorted by the given comparator
     * @param researchers list of researchers
     * @param comparator example comparator (by date, citation, etc.)
     */
    public static void printAllResearchPapers(Vector<Researcher> researchers, Comparator<ResearchPaper> comparator) {
        Vector<ResearchPaper> allPapers = new Vector<>();

        for (Researcher r : researchers) {
            allPapers.addAll(r.getResearchPapers());
        }
        allPapers.sort(comparator);
        for (ResearchPaper rp : allPapers) {
            System.out.println(rp);
        }
    }

    /**
     * Finds and prints the top cited researcher (highest total citations) from the given list.
     * @param researchers list of researchers
     * @return max cited researcher
     */
    public static Researcher getTopCitedResearcher (Vector<Researcher> researchers) {
        Researcher top = null;
        int maxCitations = -1;

        for (Researcher r : researchers) {
            int sum = r.getResearchPapers().stream().mapToInt(ResearchPaper::getCitations).sum();

            if (sum > maxCitations) {
                maxCitations = sum;
                top = r;
            }
        }
        return top;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Researcher)) return false;
        if (!super.equals(o)) return false;
        Researcher that = (Researcher) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(researches, that.researches) &&
                Objects.equals(projects, that.projects);
    }
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), type, researches, projects);}

    @Override
    public String toString() {
        return "Researcher[[" + super.toString() +
                "], type=" + type +
                ", researches=" + researches +
                ", projects=" + projects +
                '}';
    }
}
