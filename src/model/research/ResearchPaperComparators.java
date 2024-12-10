package model.research;

import java.util.Comparator;

public class ResearchPaperComparators {
    /**
     * Comparator to sort ResearchPaper by publication date (oldest to newest).
     */
    public static final Comparator<ResearchPaper> BY_DATE = Comparator.comparing(ResearchPaper::getPublicationDate);

    /**
     * Comparator to sort ResearchPaper by publication date (newest to oldest).
     */
    public static final Comparator<ResearchPaper> BY_DATE_DESC = Comparator.comparing(ResearchPaper::getPublicationDate).reversed();

    /**
     * Comparator to sort ResearchPaper by citation count (lowest to highest).
     */
    public static final Comparator<ResearchPaper> BY_CITATIONS = Comparator.comparingInt(ResearchPaper::getCitations);

    /**
     * Comparator to sort ResearchPaper by citation count (highest to lowest).
     */
    public static final Comparator<ResearchPaper> BY_CITATIONS_DESC = Comparator.comparingInt(ResearchPaper::getCitations).reversed();

    /**
     * Comparator to sort ResearchPaper by title alphabetically (A-Z).
     */
    public static final Comparator<ResearchPaper> BY_TITLE = Comparator.comparing(ResearchPaper::getTitle);

    /**
     * Comparator to sort ResearchPaper by title in reverse order (Z-A).
     */
    public static final Comparator<ResearchPaper> BY_TITLE_DESC = Comparator.comparing(ResearchPaper::getTitle).reversed();
}
