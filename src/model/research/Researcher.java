package model.research;

import enums.CITATIONFORMAT;
import model.people.Employee;
import model.people.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Researcher extends Employee implements ResearcherInterface, Serializable {
    private static final long serialVersionUID = 9L;

    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private int hIndex;

    // Constructor
    public Researcher(User user, double salary) {
        super(user.getName(), user.getSurname(), user.getSex(), user.getBirthDate(),
                user.getEmail(), "defaultPassword", user.getPhoneNumber(), user.getCitizenship(), salary); // Consider a better way to handle passwords
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.hIndex = 0; // Initial h-index
    }

    // Getters
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public int getHIndex() {
        return hIndex;
    }

    // Methods for managing research papers and projects
    public void addResearchPaper(ResearchPaper paper) {
        this.researchPapers.add(paper);
        // Update h-index (you might want to recalculate it or increment it based on citations)
    }

    public void addResearchProject(ResearchProject project) {
        this.researchProjects.add(project);
    }

    // Other methods from ResearcherInterface

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        researchPapers.stream()
                .sorted(c)
                .forEach(System.out::println);
    }

    @Override
    public int calculateHIndex() {
        // Implement your h-index calculation logic here
        // This is a simplified example
        int[] citations = researchPapers.stream()
                .mapToInt(ResearchPaper::getCitations)
                .sorted()
                .toArray();

        int hIndex = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= citations.length - i) {
                hIndex++;
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public String getAllCitations(CITATIONFORMAT format) {
        // Implement citation formatting logic here
        return null;
    }

    @Override
    public void manageResearchProject(ResearchProject project) {
        // Add logic for managing a project (e.g., adding participants, updating status)
    }

    @Override
    public void announcePublishedPaper(ResearchPaper paper) {
        // Add logic for announcing a new paper (e.g., through a notification system)
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Researcher)) return false;
        if (!super.equals(o)) return false;
        Researcher that = (Researcher) o;
        return hIndex == that.hIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hIndex);
    }

    @Override
    public String toString() {
        return "Researcher{" +
                ", researchPapers=" + researchPapers +
                ", researchProjects=" + researchProjects +
                ", hIndex=" + hIndex +
                "} " + super.toString();
    }
}