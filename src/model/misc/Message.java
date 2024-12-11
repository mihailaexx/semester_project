package model.misc;

import model.people.Employee;
import java.util.Date;

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

    public String toString() {
        return "[" + timestamp + "] From: " + sender.getName() + " " + sender.getSurname() + ": " + text;
    }
}
