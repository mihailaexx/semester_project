package model.misc;

import model.people.Employee;
import java.util.Date;
import java.util.Objects;

public class Message {
    private Employee sender;
    private String text;
    private Date timestamp;

    public Message(Employee sender, String text) {
        this.sender = sender;
        this.text = text;
        this.timestamp = new Date();
    }

    public Employee getSender() { return sender; }
    public String getText() { return text; }
    public Date getTimestamp() { return timestamp; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Message message)) return false;
        return Objects.equals(getSender(), message.getSender()) && Objects.equals(getText(), message.getText()) && Objects.equals(getTimestamp(), message.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSender(), getText(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Message[" +
                "sender=" + sender +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ']';
    }
}
