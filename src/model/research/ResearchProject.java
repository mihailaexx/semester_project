package model.research;

import exceptions.NonResearcherJoinProjectException;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 11L;

    private String topic;
    private Vector<ResearchPaper> publishedPapers;
    private Vector<ResearcherInterface> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new Vector<>();
        this.participants = new Vector<>();
    }

    public void addParticipant(Object participant) {
        // Check if participant is a Researcher
        if ( !(participant instanceof ResearcherInterface) ) {
            throw new NonResearcherJoinProjectException("Only a Researcher can join a ResearchProject!");
        }
        participants.add((ResearcherInterface) participant);
    }

    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public String getTopic() {return topic;}
    public Vector<ResearchPaper> getPublishedPapers() {return publishedPapers;}
    public Vector<ResearcherInterface> getParticipants() {return participants;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(topic, that.topic) &&
                Objects.equals(publishedPapers, that.publishedPapers) &&
                Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, publishedPapers, participants);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", publishedPapers=" + publishedPapers +
                ", participants=" + participants +
                '}';
    }
}
