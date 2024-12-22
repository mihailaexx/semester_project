package model.research;

import enums.CITATIONFORMAT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ResearchPaper implements Serializable {
    private static final long serialVersionUID = 10L;

    private static int nextId = 1;

    private int paperId;
    private String title;
    private List<String> authors;
    private Date publicationDate;
    private String journal;
    private String doi;
    private List<String> keywords;
    private int citations;

    public ResearchPaper(String title, List<String> authors, Date publicationDate, String journal, String doi, int citations) {
        this.paperId = generatePaperId();
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.journal = journal;
        this.doi = doi;
        this.citations = citations;
        this.keywords = new ArrayList<>();
    }

    // Getters
    public int getPaperId() {
        return paperId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getJournal() {
        return journal;
    }

    public String getDoi() {
        return doi;
    }

    public int getCitations() {
        return citations;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    // Setters
    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }


    private synchronized int generatePaperId() {
        return nextId++;
    }

    public String getCitation(CITATIONFORMAT format) {
        switch (format) {
            case PLAIN_TEXT:
                return String.format("%s. %s. %s, %d. DOI: %s",
                        title, String.join(", ", authors), journal, (1900 + publicationDate.getYear()), doi);
            case BIBTEX:
                return String.format("@article{%s,\n" +
                                "  title={%s},\n" +
                                "  author={%s},\n" +
                                "  journal={%s},\n" +
                                "  year={%d},\n" +
                                "  doi={%s}\n" +
                                "}",
                        doi, title, String.join(" and ", authors),
                        journal, (1900 + publicationDate.getYear()), doi);
            default:
                return title;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return paperId == that.paperId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperId);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "paperId=" + paperId +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationDate=" + publicationDate +
                ", journal='" + journal + '\'' +
                ", doi='" + doi + '\'' +
                ", keywords=" + keywords +
                ", citations=" + citations +
                '}';
    }
}