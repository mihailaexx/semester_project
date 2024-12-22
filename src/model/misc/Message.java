package model.misc;

import model.people.User;

import java.util.Date;
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 19L;

    private User sender;
    private User recipient;
    private String messageText;
    private Date sentAt;

    public Message(User sender, User recipient, String messageText) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.sentAt = new Date();
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public Date getSentAt() {
        return sentAt;
    }

    @Override
    public String toString() {
        return "From: " + sender.getUsername() + "\n" +
                "To: " + recipient.getUsername() + "\n" +
                "Sent at: " + sentAt + "\n" +
                "Message: " + messageText;
    }
}