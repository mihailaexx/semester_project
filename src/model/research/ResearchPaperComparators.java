package model.research;

import java.util.Comparator;

public class ResearchPaperComparators {
    public static final Comparator<ResearchPaper> BY_DATE = Comparator.comparing(ResearchPaper::getPublicationDate);
    public static final Comparator<ResearchPaper> BY_DATE_DESC = Comparator.comparing(ResearchPaper::getPublicationDate).reversed();
    public static final Comparator<ResearchPaper> BY_CITATIONS = Comparator.comparingInt(ResearchPaper::getCitations);
    public static final Comparator<ResearchPaper> BY_CITATIONS_DESC = Comparator.comparingInt(ResearchPaper::getCitations).reversed();
    public static final Comparator<ResearchPaper> BY_TITLE = Comparator.comparing(ResearchPaper::getTitle);
    public static final Comparator<ResearchPaper> BY_TITLE_DESC = Comparator.comparing(ResearchPaper::getTitle).reversed();
}
