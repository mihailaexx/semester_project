package view;

import controller.EmployeeController;
import model.people.Employee;

import java.util.Scanner;

public class EmployeeView {
    private final Scanner scanner;
    private EmployeeController employeeController;

    public EmployeeView(Scanner scanner) {
        this.scanner = scanner;
    }
    public void setEmployeeController(EmployeeController controller) {
        this.employeeController = controller;
    }

    public String promptForRecipientUsername() {
        System.out.print("Enter the username of the recipient: ");
        return scanner.nextLine();
    }

    public String promptForMessageText() {
        System.out.print("Enter the message: ");
        return scanner.nextLine();
    }

    public void displayMessageSentConfirmation() {
        System.out.println("Message sent successfully.");
    }

    public void displayMessageSendingFailure(String reason) {
        System.err.println("Failed to send message: " + reason);
    }

    public int displayEmployeeMenu() {
        System.out.println("\nEmployee Menu:");
        System.out.println("1. Send Message");
        System.out.println("2. View Messages"); // Add this option
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    // Add a method to display messages
    public void displayMessages(Employee employee) {
        System.out.println("\nMessages for " + employee.getName() + " " + employee.getSurname() + ":");
        // In a real application, you would retrieve messages from the data store
        // For now, just print a placeholder message
        System.out.println("No messages to display.");
    }

    public void sendMessage(Employee employee) {
        String recipientUsername = promptForRecipientUsername();
        String messageText = promptForMessageText();

        if (recipientUsername.isEmpty() || messageText.isEmpty()) {
            System.out.println("Recipient username or message cannot be empty.");
            return;
        }

        try {
            employeeController.handleSendMessage(employee, recipientUsername, messageText);
            displayMessageSentConfirmation();
        } catch (Exception e) {
            displayMessageSendingFailure(e.getMessage());
        }
    }
}