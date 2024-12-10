package model.research;

import enums.CITATIONFORMAT;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class ResearchPaper {
    private String title;
    private Vector<String> authors;
    private Date publicationDate;
    private String journal;
    private String doi;
    private Vector<String> references;
    private Vector<String> keywords;
    private int citations;

    public ResearchPaper(String title, Vector<String> authors, Date publicationDate, String journal, String doi, Vector<String> references, int citations) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.journal = journal;
        this.doi = doi;
        this.references = references;
        this.citations = citations;
    }

    public int getCitations() {return citations;}
    public String getDoi() {return doi;}
    public String getTitle() {return title;}
    public Vector<String> getAuthors() {return authors;}
    public Date getPublicationDate() {return publicationDate;}
    public String getJournal() {return journal;}
    public Vector<String> getReferences() {return references;}

    public String getCitation(CITATIONFORMAT format) {
        switch (format) {
            case PLAIN_TEXT:
                //Plain text citation format
                return title + ". " + String.join(", ", authors) + ". " + journal + ", " + (1900+publicationDate.getYear()) + ". DOI: " + doi;
            case BIBTEX:
                //BibTex format
                return "@article{" + doi + ",\n" +
                        "  title={" + title + "},\n" +
                        "  author={" + String.join(" and ", authors) + "},\n" +
                        "  journal={" + journal + "},\n" +
                        "  year={" + (1900+publicationDate.getYear()) + "},\n" +
                        "  doi={" + doi + "}\n" +
                        "}";
            default:
                return title;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return  citations == that.citations &&
                Objects.equals(title, that.title) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(journal, that.journal) &&
                Objects.equals(publicationDate, that.publicationDate) &&
                Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, journal, publicationDate, doi, citations);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", journal='" + journal + '\'' +
                ", publicationDate=" + publicationDate +
                ", citations=" + citations +
                '}';
    }
}
