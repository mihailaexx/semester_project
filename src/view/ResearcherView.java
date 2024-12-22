package view;

import data.DataStore;
import exceptions.NonResearcherJoinProjectException;
import model.people.User;
import model.research.ResearchPaper;
import model.research.ResearchProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ResearcherView {
    private final Scanner scanner;

    public ResearcherView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayResearcherMenu() {
        System.out.println("\nResearcher Menu:");
        System.out.println("1. Print Papers Sorted");
        System.out.println("2. Calculate H-Index");
        System.out.println("3. Get All Citations in Format");
        System.out.println("4. Manage Research Project");
        System.out.println("5. Announce Published Paper");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        return choice;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    public void displayAllCitations(String citations) {
        System.out.println("\nAll Citations:");
        System.out.println(citations);
    }

    public void displayResearchPapers(List<ResearchPaper> papers) {
        System.out.println("\nResearch Papers:");
        if (papers.isEmpty()) {
            System.out.println("No research papers found.");
        } else {
            for (ResearchPaper paper : papers) {
                System.out.println(paper);
            }
        }
    }

    public String promptForSortingOrder() {
        System.out.print("Enter sorting order (e.g., 'date', 'citations', 'title'): ");
        return scanner.nextLine();
    }

    public String promptForCitationFormat() {
        System.out.print("Enter citation format (e.g., 'plain', 'bibtex'): ");
        return scanner.nextLine();
    }

    public void displayHIndex(int hIndex) {
        System.out.println("H-Index: " + hIndex);
    }

    public int promptForProjectId() {
        System.out.print("Enter project ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public int promptForPaperId() {
        System.out.print("Enter paper ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public void manageResearchProject(ResearchProject project, DataStore dataStore) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nManaging Project: " + project.getTopic());
            System.out.println("1. Add Participant");
            System.out.println("2. Publish Paper");
            System.out.println("3. View Project Details");
            System.out.println("4. Update Project Topic");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addParticipantToProject(project, dataStore);
                    break;
                case 2:
                    publishPaperToProject(project, dataStore);
                    break;
                case 3:
                    viewProjectDetails(project);
                    break;
                case 4:
                    updateProjectTopic(project);
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addParticipantToProject(ResearchProject project, DataStore dataStore) {
        System.out.print("Enter the username of the researcher to add: ");
        String username = scanner.nextLine();

        User user = dataStore.getUserByUsername(username);

        if (user != null) {
            if (user.getResearcher() == null) {
                System.err.println("User not found or user is not a researcher.");
                return;
            }
            if (project.getParticipants().contains(user.getResearcher())) {
                System.err.println("Researcher already added to project");
                return;
            }
            try {
                project.addParticipant(user.getResearcher());
                System.out.println("Researcher " + username + " added to project.");
            } catch (NonResearcherJoinProjectException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("User not found");
        }
    }

    private void publishPaperToProject(ResearchProject project, DataStore dataStore) {
        System.out.print("Enter the ID of the paper to publish: ");
        int paperId = Integer.parseInt(scanner.nextLine());

        ResearchPaper paper = dataStore.getAllResearchPapers().stream()
                .filter(p -> p.getPaperId() == paperId)
                .findFirst()
                .orElse(null);

        if (paper != null) {
            project.publishPaper(paper);
            System.out.println("Paper with ID '" + paperId + "' published to project.");
        } else {
            System.err.println("Paper not found with ID: " + paperId);
        }
    }

    private void viewProjectDetails(ResearchProject project) {
        System.out.println("Project Topic: " + project.getTopic());
        System.out.println("Participants: ");
        project.getParticipants().forEach(p -> System.out.println("- " + p.getUser().getName()));
        System.out.println("Published Papers: ");
        project.getPublishedPapers().forEach(p -> System.out.println("- " + p.getTitle()));
    }

    private void updateProjectTopic(ResearchProject project) {
        System.out.print("Enter new project topic: ");
        String newTopic = scanner.nextLine();
        project.setTopic(newTopic);
        System.out.println("Project topic updated to: " + newTopic);
    }

    public void announcePublishedPaper(ResearchPaper paper) {
        System.out.println("\nANNOUNCEMENT: New Research Paper Published!");
        System.out.println("Title: " + paper.getTitle());
        System.out.println("Authors: " + String.join(", ", paper.getAuthors()));
        System.out.println("Journal: " + paper.getJournal());
        System.out.println("DOI: " + paper.getDoi());
        System.out.println("Published Date: " + paper.getPublicationDate());
        System.out.println("----------------------------------------\n");
    }

    public ResearchPaper promptForResearchPaperDetails() {
        scanner.nextLine();
        System.out.print("Enter the title of the research paper: ");
        String title = scanner.nextLine();

        List<String> authors = new ArrayList<>();
        System.out.print("Enter the number of authors: ");
        int numAuthors = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numAuthors; i++) {
            System.out.print("Enter author #" + (i + 1) + ": ");
            authors.add(scanner.nextLine());
        }

        System.out.print("Enter the journal name: ");
        String journal = scanner.nextLine();

        System.out.print("Enter the publication date (yyyy-MM-dd): ");
        Date publicationDate = null;
        while (publicationDate == null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                publicationDate = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        System.out.print("Enter the DOI: ");
        String doi = scanner.nextLine();

        System.out.print("Enter the number of citations: ");
        int citations = scanner.nextInt();
        scanner.nextLine();

        return new ResearchPaper(title, authors, publicationDate, journal, doi, citations);
    }

    public String promptForResearchProjectTopic() {
        System.out.print("Enter the topic of the research project: ");
        return scanner.nextLine();
    }
}