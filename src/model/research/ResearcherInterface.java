package model.research;

import enums.CITATIONFORMAT;

import java.util.Comparator;
import java.util.List;

public interface ResearcherInterface {
    void printPapers(Comparator<ResearchPaper> c);
    int calculateHIndex();
    String getAllCitations(CITATIONFORMAT format);
    void manageResearchProject(ResearchProject project);
    void announcePublishedPaper(ResearchPaper paper);

    List<ResearchPaper> getResearchPapers();
}