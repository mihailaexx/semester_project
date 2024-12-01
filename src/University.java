import java.util.Vector;

public class University {
    private String name;
    private Government rector;
    private Vector<School> schools;

    private Vector<ResearchPaper> allResearchPapers;
    private Vector<String> log; // or txt file

    public University(String name) {
        this.name = name;
        this.schools = new Vector<School>();
        this.allResearchPapers = new Vector<ResearchPaper>();
        this.log = new Vector<String>();
    }

    public void addRector(Government rector) {
        this.rector = rector;
    }

    public void addSchool(School school) {
        schools.add(school);
    }

    public void addResearchPaper(ResearchPaper researchPaper) {
        allResearchPapers.add(researchPaper);
    }

    public void addLog(String log) {
        this.log.add(log);
    }
}
