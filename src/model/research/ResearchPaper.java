package model.research;

import java.util.Date;
import java.util.Vector;

public class ResearchPaper {
    private String title;
    private String description;
    private Vector<Researcher> authors;
    private Date publicationDate;
    private String location;
    private Vector<String> references;
    private Vector<String> keywords;
    private int quotationCount;
}
