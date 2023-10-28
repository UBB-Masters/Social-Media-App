package UI;

import Controller.ServerController;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;
import Entities.Misc.Email;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServerUI {

    //hash set with users
    public static void main(String[] args) {
        ServerController serverController = new ServerController(
                new InMemoryUserRepository(), new InMemoryMessageRepository()
        );

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    addUser(serverController, scanner);
                    break;
                case 2:
                    // Add more functionalities here based on your menu
                    System.out.println("TODO");
                    break;
                case 3:
                    // Add more functionalities here based on your menu
                    break;
                case 4:
                    displayAllUsers(serverController);
                    break;
                case 5:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    private static void displayAllUsers(ServerController serverController) {
        System.out.println("Printing all users:");
        for (User user : serverController.getAllUsers()) {
            System.out.println(user); // Assuming toString() in User class provides necessary information
        }
    }

    private static void addUser(ServerController serverController, Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine().trim();

        System.out.println("Enter password:");
        String password = scanner.nextLine().trim();

        System.out.println("Enter birthdate (YYYY-MM-DD):");
        String birthDateString = scanner.nextLine().trim();
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();

        // Default visibility can be added similarly if needed

        // Create a new user
        User newUser = new User(username, password, birthDate, email, User.Visibility.PUBLIC);

        // Add the user via the controller
        serverController.addUser(newUser);
        System.out.println("User added successfully!");
    }

    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add User");
        System.out.println("2. [To be implemented]");
        System.out.println("3. [To be implemented]");
        System.out.println("4. Retrieve all users");
        System.out.println("5. Exit");
    }




}
