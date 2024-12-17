package view;

import java.util.Scanner;

public class FinanceManagerView {
    private final Scanner scanner;

    public FinanceManagerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayFinanceManagerMenu() {
        System.out.println("\n Finance Manager Menu:");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}
