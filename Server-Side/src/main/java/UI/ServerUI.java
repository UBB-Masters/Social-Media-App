package UI;

import Controller.ServerController;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;
import Entities.Misc.Email;
import io.vavr.API;
import io.vavr.control.Option;
import io.vavr.control.Try;

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
            Option<Integer> inputOption = readInput(scanner);
            choice = inputOption.getOrElse(-1);
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    addUser(serverController, scanner);
                    break;
                case 2:
                    removeUser(serverController, scanner);
                    break;
                case 3:
                    updateUser(serverController, scanner);
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

    private static Option<Integer> readInput(Scanner scanner) {
        System.out.println("Enter your choice:");
        return Try.of(scanner::nextInt).toOption();

    }

    private static void displayAllUsers(ServerController serverController) {
        System.out.println("Printing all users:");
        serverController.getAllUsers()
                .forEach(System.out::println); // Assuming toString() in User class provides necessary information
    }

    private static void addUser(ServerController serverController, Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine().trim();

        System.out.println("Enter password:");
        String password = scanner.nextLine().trim();

        System.out.println("Enter birthdate (YYYY-MM-DD):");
        Date birthDate = Try.of(() -> new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine().trim()))
                .getOrElseThrow(() -> new RuntimeException("Invalid date format"));

        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();

        System.out.println("Enter visibility (PRIVATE, FRIENDS, PUBLIC):");
        User.Visibility visibility = Try.of(() -> User.Visibility.valueOf(scanner.nextLine().trim().toUpperCase()))
                .getOrElseThrow(() -> new RuntimeException("Invalid visibility"));

        User newUser = new User(username, password, birthDate, email, visibility);

        Try<Void> addUserAttempt = Try.run(() -> serverController.addUser(newUser));
        addUserAttempt.onSuccess(ignore -> System.out.println("User added successfully!"))
                .onFailure(error -> System.out.println("Failed to add user: " + error.getMessage()));
    }

    private static void removeUser(ServerController serverController, Scanner scanner) {
        System.out.println("Enter ID of the user to remove:");
        Option<Integer> idOption = readInput(scanner);
        idOption.peek(id -> {
            Try.of(() -> serverController.removeUserByID(id))
                    .onSuccess(ignored -> System.out.println("User removed successfully!"))
                    .onFailure(error -> System.out.println("Failed to remove user: " + error.getMessage()));
        }).onEmpty(() -> System.out.println("Invalid ID"));
    }

    private static void updateUser(ServerController serverController, Scanner scanner) {
        //TODO: Implement
    }




    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. [To be implemented]");
        System.out.println("4. Retrieve all users");
        System.out.println("5. Exit");
    }
}
