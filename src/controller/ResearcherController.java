package controller;

import data.DataStore;
import model.people.User;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.Researcher;
import service.ResearcherService;
import view.ResearcherView;

import java.util.Comparator;
import java.util.List;

public class ResearcherController {
    private final ResearcherService researcherService;
    private final ResearcherView researcherView;
    private final DataStore dataStore;

    public ResearcherController(ResearcherService researcherService, ResearcherView researcherView, DataStore dataStore) {
        this.researcherService = researcherService;
        this.researcherView = researcherView;
        this.dataStore = dataStore;
    }

    public void handleResearcherMenu(User user) {
        Researcher researcher = user.getResearcher();
        while (true) {
            int choice = researcherView.displayResearcherMenu();
            switch (choice) {
                case 1:
                    printPapers(researcher);
                    break;
                case 2:
                    calculateHIndex(researcher);
                    break;
                case 3:
                    getAllCitations(researcher);
                    break;
                case 4:
                    manageResearchProject(researcher);
                    break;
                case 5:
                    announcePublishedPaper(researcher);
                    break;
                case 0:
                    return; // Back to Main Menu
                default:
                    researcherView.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void printPapers(Researcher researcher) {
        String order = researcherView.promptForSortingOrder();
        Comparator<ResearchPaper> comparator = getComparator(order);
        List<ResearchPaper> sortedPapers = researcherService.getPapers(researcher, comparator);
        researcherView.displayResearchPapers(sortedPapers);
    }

    private Comparator<ResearchPaper> getComparator(String order) {
        switch (order.toLowerCase()) {
            case "date":
                return Comparator.comparing(ResearchPaper::getPublicationDate);
            case "citations":
                return Comparator.comparingInt(ResearchPaper::getCitations);
            case "title":
                return Comparator.comparing(ResearchPaper::getTitle);
            default:
                throw new IllegalArgumentException("Invalid sorting order: " + order);
        }
    }

    private void calculateHIndex(Researcher researcher) {
        int hIndex = researcherService.calculateHIndex(researcher);
        researcherView.displayHIndex(hIndex);
    }

    private void getAllCitations(Researcher researcher) {
        String format = researcherView.promptForCitationFormat();
        String citations = researcherService.getAllCitations(researcher, format);
        researcherView.displayAllCitations(citations);
    }

    private void manageResearchProject(Researcher researcher) {
        int projectId = researcherView.promptForProjectId();
        researcherService.manageResearchProject(researcher.getResearcherId(), projectId);
    }

    private void announcePublishedPaper(Researcher researcher) {
        int paperId = researcherView.promptForPaperId();
        researcherService.announcePublishedPaper(researcher.getResearcherId(), paperId);
    }

    public void addResearchPaper(Researcher researcher) {
        ResearchPaper paper = researcherView.promptForResearchPaperDetails();
        try {
            researcherService.addResearchPaper(researcher.getResearcherId(), paper);
            researcherView.displayMessage("Research paper added successfully.");
        } catch (Exception e) {
            researcherView.displayErrorMessage("Error adding research paper: " + e.getMessage());
        }
    }

    public void addResearchProject(Researcher researcher) {
        String topic = researcherView.promptForResearchProjectTopic();
        try {
            ResearchProject project = new ResearchProject(topic);
            researcherService.addResearchProject(researcher.getResearcherId(), project);
            researcherView.displayMessage("Research project added successfully.");
        } catch (Exception e) {
            researcherView.displayErrorMessage("Error adding research project: " + e.getMessage());
        }
    }
}