package service;

import data.DataStore;
import enums.CITATIONFORMAT;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.Researcher;
import view.ResearcherView;

import java.util.Comparator;
import java.util.List;

public class ResearcherService {
    private final DataStore dataStore;
    private final ResearcherView researcherView;

    public ResearcherService(DataStore dataStore, ResearcherView researcherView) {
        this.dataStore = dataStore;
        this.researcherView = researcherView;
    }

    public void addResearchPaper(int researcherId, ResearchPaper paper) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            researcher.addResearchPaper(paper);
            dataStore.saveResearcher(researcher);
            dataStore.saveResearchPaper(paper);
        } else {
            System.err.println("Researcher not found with ID: " + researcherId);
        }
    }

    public List<ResearchPaper> getResearchPapers(int researcherId) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            return researcher.getResearchPapers();
        } else {
            System.err.println("Researcher not found with ID: " + researcherId);
            return List.of();
        }
    }

    public int calculateHIndex(Researcher researcher) {
        return researcher.calculateHIndex();
    }

    public List<ResearchPaper> getPapers(Researcher researcher, Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> papers = researcher.getResearchPapers();
        papers.sort(comparator);
        return papers;
    }

    public void addResearchProject(int researcherId, ResearchProject project) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            researcher.addResearchProject(project);
            dataStore.saveResearcher(researcher);
        } else {
            System.err.println("Researcher not found with ID: " + researcherId);
        }
    }

    public List<ResearchProject> getResearchProjectsByResearcher(int researcherId) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            return researcher.getResearchProjects();
        } else {
            System.err.println("Researcher not found with ID: " + researcherId);
            return List.of();
        }
    }

    public void manageResearchProject(int researcherId, int projectId) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            ResearchProject project = researcher.getResearchProjects().stream()
                    .filter(p -> p.getProjectId() == projectId)
                    .findFirst()
                    .orElse(null);

            if (project != null) {
                researcherView.manageResearchProject(project, dataStore);
            } else {
                researcherView.displayErrorMessage("Research project not found with ID: " + projectId);
            }
        } else {
            researcherView.displayErrorMessage("Researcher not found with ID: " + researcherId);
        }
    }

    public void announcePublishedPaper(int researcherId, int paperId) {
        Researcher researcher = dataStore.getResearcherById(researcherId);
        if (researcher != null) {
            ResearchPaper paper = researcher.getResearchPapers().stream()
                    .filter(p -> p.getPaperId() == paperId)
                    .findFirst()
                    .orElse(null);

            if (paper != null) {
                // Display the announcement
                researcherView.announcePublishedPaper(paper);
            } else {
                researcherView.displayErrorMessage("Research paper not found with ID: " + paperId);
            }
        } else {
            researcherView.displayErrorMessage("Researcher not found with ID: " + researcherId);
        }
    }

    public String getAllCitations(Researcher researcher, String format) {
        CITATIONFORMAT citationFormat;
        try {
            citationFormat = CITATIONFORMAT.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid citation format: " + format);
            return "";
        }

        StringBuilder citations = new StringBuilder();
        for (ResearchPaper paper : researcher.getResearchPapers()) {
            citations.append(paper.getCitation(citationFormat)).append("\n");
        }
        return citations.toString();
    }

    public Researcher findTopCitedResearcher(int year) {
        return dataStore.getAllResearchers().stream()
                .max(Comparator.comparingInt(r -> r.getResearchPapers().stream()
                        .filter(p -> p.getPublicationDate().getYear() == year)
                        .mapToInt(ResearchPaper::getCitations)
                        .sum()))
                .orElse(null);
    }

    public Researcher findTopCitedResearcherBySchool(String schoolName) {
        return dataStore.getAllResearchers().stream()
                .max(Comparator.comparingInt(r -> r.getResearchPapers().stream()
                        .filter(p -> p.getJournal().toLowerCase().contains(schoolName.toLowerCase()))
                        .mapToInt(ResearchPaper::getCitations)
                        .sum()))
                .orElse(null);
    }
}
