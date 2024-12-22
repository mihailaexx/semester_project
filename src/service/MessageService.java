package service;

import data.DataStore;
import model.misc.Message;
import model.people.Employee;
import model.people.User;

import java.util.List;

public class MessageService {
    private final DataStore dataStore;

    public MessageService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void sendMessage(String senderId, String recipientUsername, String messageText) {
        User sender = dataStore.getEmployeeById(senderId);
        User recipient = dataStore.getUserByUsername(recipientUsername);

        if (sender == null || recipient == null) {
            System.err.println("Invalid sender or recipient ID.");
            return;
        }

        Message message = new Message(sender, recipient, messageText);
        dataStore.saveMessage(message);
    }

    public List<Message> getMessagesForUser(String username) {
        User user = dataStore.getUserByUsername(username);
        if (user == null) {
            System.err.println("User not found with username " + username);
            return List.of();
        }
        return dataStore.getMessagesForUser(user);
    }
}