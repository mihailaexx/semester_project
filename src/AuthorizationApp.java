import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import enums.*;

public class AuthorizationApp {

    private static final Map<String, String> users = new HashMap<>(); // In-memory user storage (INSECURE for production)

    static {
        // Add some sample users (replace with secure user management in a real application)
        users.put("root", "root");
        users.put("user1", "12345678");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Console Application!");

        while (true) {
            System.out.println("\nPlease enter your username (or 'exit' to quit):");
            String username = scanner.nextLine();

            if (username.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Please enter your password:");
            String password = scanner.nextLine();

            if (authenticate(username, password)) {
                System.out.println("Authentication successful!");
                handleAuthorizedUser(username, scanner);
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting application.");
    }

    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void handleAuthorizedUser(String username, Scanner scanner) {
        if (username.equals("root")) {
            adminMenu(scanner);
        } else {
            userMenu(scanner);
        }
    }

    private static void adminMenu(Scanner scanner) {
        System.out.println("\nmodel.people.Admin Menu:");
        System.out.println("1. List all users");
        System.out.println("2. Exit");

        int choice = getIntegerInput(scanner, 1, 2);

        switch (choice) {
            case 1:
                listAllUsers();
                break;
            case 2:
                break;
        }
    }

    private static void userMenu(Scanner scanner) {
        System.out.println("\nmodel.people.User Menu:");
        System.out.println("1. View Profile");
        System.out.println("2. Exit");

        int choice = getIntegerInput(scanner, 1, 2);

        switch (choice) {
            case 1:
                viewProfile();
                break;
            case 2:
                break;
        }
    }

    private static void listAllUsers() {
        System.out.println("\nList of users:");
        for (String user : users.keySet()) {
            System.out.println("- " + user);
        }
    }

    private static void viewProfile() {
        System.out.println("\nViewing your profile... (Implementation needed)");
    }

    private static int getIntegerInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
