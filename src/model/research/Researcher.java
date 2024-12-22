package model.research;

import enums.CITATIONFORMAT;
import model.people.Employee;
import model.people.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Researcher  implements Serializable {
    private static final long serialVersionUID = 9L;

    private int researcherId;
    private static int nextResearcherId = 1;
    private User user;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private int hIndex;

    public Researcher(User user) {
        this.user = user;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.researcherId = generateResearcherId();
        this.hIndex = 0;
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

    public User getUser() {
        return user;
    }
    public Integer getResearcherId() {
        return researcherId;
    }
    public void setResearcherId(Integer researcherId) {
        this.researcherId = researcherId;
    }
    public int generateResearcherId() {
        return nextResearcherId++;
    }

    public void addResearchPaper(ResearchPaper paper) {
        this.researchPapers.add(paper);
        calculateHIndex();
    }

    public void addResearchProject(ResearchProject project) {
        this.researchProjects.add(project);
    }

    public List<String> printPapers(Comparator<ResearchPaper> c) {
        List<String> paperList = new ArrayList<>();
        researchPapers.stream()
                .sorted(c)
                .map(ResearchPaper::toString)
                .forEach(paperList::add);
        return paperList;
    }

    public int calculateHIndex() {
        int[] citations = researchPapers.stream()
                .mapToInt(ResearchPaper::getCitations)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();


        int hIndex = 0;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= i + 1) {
                hIndex++;
            } else {
                break;
            }
        }
        this.hIndex = hIndex;
        return hIndex;
    }

    public String getAllCitations(CITATIONFORMAT format) {
        StringBuilder citations = new StringBuilder();
        for (ResearchPaper paper : researchPapers) {
            citations.append(paper.getCitation(format)).append("\n");
        }
        return citations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Researcher)) return false;
        Researcher that = (Researcher) o;
        return researcherId == that.researcherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(researcherId);
    }

    @Override
    public String toString() {
        return "Researcher{" +
                "researcherId=" + researcherId +
                ", user=" + user +
                ", researchPapers=" + researchPapers +
                ", researchProjects=" + researchProjects +
                ", hIndex=" + hIndex +
                '}';
    }

}