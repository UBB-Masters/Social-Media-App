package UI;

import Controller.ServerController;
import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;
import Entities.Misc.Email;
import io.vavr.API;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                    sendMessage(serverController, scanner); // Option to send a message
                    break;
                case 6:
                    displaySentMessages(serverController, scanner); // Option to see all sent messages
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 7);
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
        System.out.println("Enter ID of the user to update:");
        Option<Integer> idOption = readInput(scanner);

        idOption.peek(id -> {
            User user = serverController.getUserById(id);
            if (user == null) {
                System.out.println("User not found");
                return;
            }

            // Clear the input buffer
            scanner.nextLine();

            System.out.println("Enter new username:");
            String username = scanner.nextLine().trim();

            System.out.println("Enter new password:");
            String password = scanner.nextLine().trim();

            System.out.println("Enter new birthdate (YYYY-MM-DD):");
            Date birthDate = Try.of(() -> new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine().trim()))
                    .getOrElseThrow(() -> new RuntimeException("Invalid date format"));

            System.out.println("Enter new email:");
            String email = scanner.nextLine().trim();

            System.out.println("Enter new visibility (PRIVATE, FRIENDS, PUBLIC):");
            User.Visibility visibility = Try.of(() -> User.Visibility.valueOf(scanner.nextLine().trim().toUpperCase()))
                    .getOrElseThrow(() -> new RuntimeException("Invalid visibility"));

            User newUser = new User(username, password, birthDate, email, visibility);

            Try<Void> updateUserAttempt = Try.run(() -> serverController.updateUser(user, newUser));
            updateUserAttempt.onSuccess(ignore -> System.out.println("User updated successfully!"))
                    .onFailure(error -> System.out.println("Failed to update user: " + error.getMessage()));
        }).onEmpty(() -> System.out.println("Invalid ID"));
    }

    private static void sendMessage(ServerController serverController, Scanner scanner) {
        System.out.println("Enter sender ID:");
        Option<Integer> senderIdOption = readInput(scanner);
        System.out.println("Enter receiver ID:");
        Option<Integer> receiverIdOption = readInput(scanner);

        String message = null;
        User sender;
        User receiver;

        if (senderIdOption.isDefined() && receiverIdOption.isDefined()) {
            sender = serverController.getUserById(senderIdOption.get());
            receiver = serverController.getUserById(receiverIdOption.get());

            if (sender != null && receiver != null) {
                scanner.nextLine();
                System.out.println("Enter the message:");
                Option<String> messageOption = readMessageInput(scanner);
                if (messageOption.isDefined()) {
                    message = messageOption.get();
                    serverController.sendMessage(sender, receiver, message);
                    System.out.println("Message sent successfully!");
                } else {
                    System.out.println("Invalid message");
                }
            } else {
                System.out.println("Invalid sender or receiver ID");
            }
        } else {
            System.out.println("Invalid input for sender or receiver ID");
        }
    }

    private static Option<String> readMessageInput(Scanner scanner) {
        String message = scanner.nextLine().trim();
        return Option.of(message);
    }


    private static void displaySentMessages(ServerController serverController, Scanner scanner) {
        System.out.println("Enter sender ID:");
        Option<Integer> senderIdOption = readInput(scanner);

        if (senderIdOption.isDefined()) {
            User sender = serverController.getUserById(senderIdOption.get());

            if (sender != null) {
                ArrayList<MessageFactory> sentMessages = serverController.getSentMessages(sender);
                if (!sentMessages.isEmpty()) {
                    System.out.println("Messages sent by " + sender.getUsername() + ":");
                    sentMessages.forEach(System.out::println);
                } else {
                    System.out.println("No messages found for " + sender.getUsername());
                }
            } else {
                System.out.println("Invalid sender ID");
            }
        } else {
            System.out.println("Invalid input for sender ID");
        }
    }





    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4. Retrieve all users");
        System.out.println("5. Send a message");
        System.out.println("6. See all your sent messages");
        System.out.println("7. Exit");
    }
}
