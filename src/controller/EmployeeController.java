package controller;

import service.MessageService;
import view.EmployeeView;
import model.people.Employee;

public class EmployeeController {
    private final MessageService messageService;
    private final EmployeeView employeeView;

    public EmployeeController(MessageService messageService, EmployeeView employeeView) {
        this.messageService = messageService;
        this.employeeView = employeeView;
    }

    public void handleSendMessage(Employee sender, String recipientUsername, String messageText) {
        try {
            messageService.sendMessage(sender.getEmployeeId(), recipientUsername, messageText);
            employeeView.displayMessageSentConfirmation();
        } catch (Exception e) {
            employeeView.displayMessageSendingFailure(e.getMessage());
        }
    }
}