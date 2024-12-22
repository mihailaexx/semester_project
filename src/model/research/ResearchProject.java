package model.research;

import exceptions.NonResearcherJoinProjectException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 11L;

    private static int nextId = 1;

    private int projectId;
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.projectId = generateProjectId();
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Object participant) throws NonResearcherJoinProjectException {
        if (!(participant instanceof Researcher)) {
            throw new NonResearcherJoinProjectException("Only a Researcher can join a ResearchProject!");
        }
        participants.add((Researcher) participant);
    }

    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    private synchronized int generateProjectId() {
        return nextId++;
    }

    // Getters
    public int getProjectId() {
        return projectId;
    }

    public String getTopic() {
        return topic;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    // Setters
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        ResearchProject that = (ResearchProject) o;
        return projectId == that.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "projectId=" + projectId +
                ", topic='" + topic + '\'' +
                ", publishedPapers=" + publishedPapers +
                ", participants=" + participants +
                '}';
    }
}