package Controller;

import Controller.Services.MessageRequest;
import Controller.Services.UserPostProxy;
import Controller.Services.UserService;
import Entities.Events.Events;
import Entities.Message.MessageFactory;
import Entities.Post.Comment;
import Entities.Post.Hashtag;
import Entities.Post.Post;
import Entities.Reaction.Reaction;
import Entities.Reaction.ReactionFactory;
import Entities.User.User;
import Proxy.PostProxy;
import Strategy.ReactionStrategy;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = "Entities")
public class UiSpring implements CommandLineRunner {

    private final RestServerController restServerController;
    private final UserService userService;

    @Autowired
    private static RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        return restTemplate;
    }

    
    @Autowired
    public UiSpring(RestServerController restServerController, UserService userService) {
        this.restServerController = restServerController;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UiSpring.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        login();


        Scanner scanner = new Scanner(System.in);

        User user1 = new User("user1", "password1", new Date(), "user1@gmail.com", User.Visibility.PUBLIC);
        User user2 = new User("user2", "password2", new Date(), "user2@gmail.com", User.Visibility.PUBLIC);

        restServerController.addUser(user1);
        restServerController.addUser(user2);
        int choice;
        do {
            displayMainMenu();
            Option<Integer> inputOption = readInput(scanner);
            choice = inputOption.getOrElse(-1);
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    userOperations(restServerController, scanner);
                    break;
                case 2:
                    eventOperations(restServerController, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 3);
    }

//    private void login() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter your username:");
//        String username = scanner.nextLine().trim();
//
//        System.out.println("Enter your password:");
//        String password = scanner.nextLine().trim();
//
//        // Perform authentication
//        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication result = new UsernamePasswordAuthenticationFilter().attemptAuthentication(null, authentication);
//
//        // Set the authentication result to the security context
//        SecurityContextHolder.getContext().setAuthentication(result);
//
//        // Check if authentication was successful
//        if (result.isAuthenticated()) {
//            UserDetails userDetails = (UserDetails) result.getPrincipal();
//            System.out.println("Login successful. Welcome, " + userDetails.getUsername() + "!");
//        } else {
//            System.out.println("Login failed. Invalid username or password.");
//            // You may choose to handle failed login differently, such as asking the user to try again.
//            // For simplicity, this example does not handle retries.
//        }
//    }

    private static void userOperations(RestServerController restServerController, Scanner scanner) {
        int choice;
        do {
            displayUserMenu();
            Option<Integer> inputOption = readInput(scanner);
            choice = inputOption.getOrElse(-1);
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    addUser(restServerController, scanner);
                    break;
                case 2:
                    removeUser(restServerController, scanner);
                    break;
                case 3:
                    updateUser(restServerController, scanner);
                    break;
                case 4:
                    displayAllUsers(restServerController);
                    break;
                case 5:
                    sendMessage(restServerController, scanner);
                    break;
                case 6:
                    displaySentMessages(restServerController, scanner);
                    break;
                case 7:
                    participateInEvent(restServerController, scanner);
                    break;
                case 8:
                    postOperations(restServerController, scanner);
                    break;
                case 9:
                    System.out.println("Going back to the main menu...");
                    return; // Exit the method to go back
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 8);
    }

    private static void eventOperations(RestServerController restServerController, Scanner scanner) {
        int choice;
        do {
            displayEventMenu();
            Option<Integer> inputOption = readInput(scanner);
            choice = inputOption.getOrElse(-1);
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    addEvent(restServerController, scanner);
                    break;
                case 2:
                    removeEvent(restServerController, scanner);
                    break;
                case 3:
                    updateEvent(restServerController, scanner);
                    break;
                case 4:
                    displayEvents(restServerController);
                    break;
                case 5:
                    displayEventParticipants(restServerController, scanner);
                    break;
                case 6:
                    displayUsersInterestedNotParticipating(restServerController, scanner);
                    break;
                case 7:
                    System.out.println("Going back to the main menu...");
                    return; // Exit the method to go back
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 7);
    }


    private static void postOperations(RestServerController restServerController, Scanner scanner) {
        int choice;
        do {
            displayPostMenu();
            Option<Integer> inputOption = readInput(scanner);
            choice = inputOption.getOrElse(-1);
            scanner.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    createPost(restServerController, scanner);
                    break;
                case 2:
                    addCommentToPost(restServerController, scanner);
                    break;
                case 3:
                    reactToPost(restServerController, scanner);
                    break;
//                case 4:
//                    addHashtagToPost(restServerController, scanner);
//                    break;
//                case 5:
//                    removeHashtagFromPost(serverController, scanner);
//                    break;
                case 6:
                    displayAllPosts(restServerController);
                    break;
                case 7:
                    displayUserPosts(restServerController, scanner);
                    break;
//                case 8:
//                    addCommentToAnotherUserPost(serverController, scanner);
//                    break;
//                case 9:
//                    reactToAnotherUserPost(serverController, scanner);
//                    break;
                case 10:
                    System.out.println("Going back to the main menu...");
                    return; // Exit the method to go back
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 8);
    }

    private static void displayMainMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. User Operations");
        System.out.println("2. Event Operations");
        System.out.println("3. Exit");
    }

    private static void displayUserMenu() {
        System.out.println("User Operations:");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4. Retrieve all users");
        System.out.println("5. Send a message");
        System.out.println("6. See all your sent messages");
        System.out.println("7. Participate in events");
        System.out.println("8. Manage your posts");
        System.out.println("9. Go back to main menu");
    }

    private static void displayEventMenu() {
        System.out.println("Event Operations:");
        System.out.println("1. Add Event");
        System.out.println("2. Remove Event");
        System.out.println("3. Update Event");
        System.out.println("4. Retrieve all events");
        System.out.println("5. See event participants");
        System.out.println("6. See interested users that are not participating in the event");
        System.out.println("7. Go back to main menu");
    }


    private static void displayPostMenu() {
        System.out.println("Post Operations:");
        System.out.println("1. Create a Post");
        System.out.println("2. Add a Comment to a Post");
        System.out.println("3. React to a Post");
        System.out.println("4. Add Hashtag to a Post");
        System.out.println("5. Remove Hashtag from a Post");
        System.out.println("6. Display All Posts");
        System.out.println("7. Display User's Posts");
        System.out.println("8. Comment to another post");
        System.out.println("9. React to another post");
        System.out.println("10. Go back to User Operations");
    }

    private static Option<Integer> readInput(Scanner scanner) {
        System.out.println("Enter your choice:");
        return Try.of(scanner::nextInt).toOption();

    }

    public static void displayAllUsers(RestServerController restServerController) {
        ResponseEntity<List<User>> response = restServerController.getAllUsers();
        List<User> users = response.getBody();
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found");
        }
    }

    private static void addUser(RestServerController serverController, Scanner scanner) {
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

        User newUser = User.createWithHashedPassword(username, password, birthDate, email, visibility);

        Try<Void> addUserAttempt = Try.run(() -> serverController.addUser(newUser));
        addUserAttempt.onSuccess(ignore -> System.out.println("User added successfully!"))
                .onFailure(error -> System.out.println("Failed to add user: " + error.getMessage()));
    }


    private static void removeUser(RestServerController serverController, Scanner scanner) {
        System.out.println("Enter ID of the user to remove:");
        Option<Long> idOption = readInput(scanner).map(Integer::longValue);
        idOption.peek(id -> {
            Try.of(() -> {
                ResponseEntity<String> response = serverController.removeUserById(id);
                if (response.getStatusCode() == HttpStatus.OK) {
                    System.out.println("User removed successfully!");
                } else {
                    System.out.println("Failed to remove user: " + response.getBody());
                }
                return response;
            }).onFailure(error -> System.out.println("Failed to remove user: " + error.getMessage()));
        }).onEmpty(() -> System.out.println("Invalid ID"));
    }
//
//
private static void updateUser(RestServerController serverController, Scanner scanner) {
    System.out.println("Enter ID of the user to update:");
    Option<Long> idOption = readInput(scanner).map(Integer::longValue);

    idOption.peek(id -> {
        ResponseEntity<User> response = serverController.getUserById(id);
        User existingUser = response.getBody();
        if (existingUser == null) {
            System.out.println("User not found");
            return;
        }

        // Clear the input buffer
        scanner.nextLine();

        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine().trim();
        existingUser.setUsername(newUsername);

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine().trim();
        existingUser.setPassword(newPassword);

        System.out.println("Enter new birthdate (YYYY-MM-DD):");
        Date newBirthdate = Try.of(() -> new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine().trim()))
                .getOrElseThrow(() -> new RuntimeException("Invalid date format"));
        existingUser.setBirthdate(newBirthdate);

        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine().trim();
        existingUser.setEmail(newEmail);

        System.out.println("Enter new visibility (PRIVATE, FRIENDS, PUBLIC):");
        User.Visibility newVisibility = Try.of(() -> User.Visibility.valueOf(scanner.nextLine().trim().toUpperCase()))
                .getOrElseThrow(() -> new RuntimeException("Invalid visibility"));
        existingUser.setDefaultVisibility(newVisibility);

        // Save the updated user
        ResponseEntity<String> updateResponse = serverController.updateUser(existingUser.getUserID(), existingUser);
        if (updateResponse.getStatusCode() == HttpStatus.OK) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Failed to update user: " + updateResponse.getBody());
        }
    }).onEmpty(() -> System.out.println("Invalid ID"));
}


    private static void sendMessage(RestServerController serverController, Scanner scanner) {
        System.out.println("Enter sender ID:");
        Option<Integer> senderIdOption = readInput(scanner);
        System.out.println("Enter receiver ID:");
        Option<Integer> receiverIdOption = readInput(scanner);

        String message = null;
        User sender;
        User receiver;

        if (senderIdOption.isDefined() && receiverIdOption.isDefined()) {
            ResponseEntity<User> senderResponse = restTemplate.getForEntity("http://localhost:8080/api/users/" + senderIdOption.get(), User.class);
            ResponseEntity<User> receiverResponse = restTemplate.getForEntity("http://localhost:8080/api/users/" + receiverIdOption.get(), User.class);

            if (senderResponse.getStatusCode() == HttpStatus.OK && receiverResponse.getStatusCode() == HttpStatus.OK) {
                sender = senderResponse.getBody();
                receiver = receiverResponse.getBody();

                scanner.nextLine();
                System.out.println("Enter the message:");
                Option<String> messageOption = readMessageInput(scanner);
                if (messageOption.isDefined()) {
                    message = messageOption.get();
                    MessageRequest messageRequest = new MessageRequest();
                    messageRequest.setSender(sender);
                    messageRequest.setReceiver(receiver);
                    messageRequest.setMessage(message);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<MessageRequest> request = new HttpEntity<>(messageRequest, headers);
                    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/messages", request, String.class);
                    if (response.getStatusCode() == HttpStatus.OK) {
                        System.out.println("Message sent successfully!");
                    } else {
                        System.out.println("Failed to send message: " + response.getBody());
                    }
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

    //TODO -> THIS IS NOT WORKING WELL
    private static void displaySentMessages(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter sender ID:");
        Option<Integer> senderIdOption = readInput(scanner);

        if (senderIdOption.isDefined()) {
            ResponseEntity<User> senderResponse = restTemplate.getForEntity("http://localhost:8080/api/users/" + senderIdOption.get(), User.class);

            if (senderResponse.getStatusCode() == HttpStatus.OK) {
                User sender = senderResponse.getBody();
                ResponseEntity<List<MessageFactory>> messagesResponse = restTemplate.exchange(
                        "http://localhost:8080/api/messages/" + senderIdOption.get(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MessageFactory>>() {});

                if (messagesResponse.getStatusCode() == HttpStatus.OK) {
                    List<MessageFactory> sentMessages = messagesResponse.getBody();

                    assert sentMessages != null;
                    if (!sentMessages.isEmpty()) {
                        assert sender != null;
                        System.out.println("Messages sent by " + sender.getUsername() + ":");

                        // Iterate through the sentMessages and display sender, receiver, and description
                        sentMessages.forEach(message ->
                                System.out.println("Sender: " + message.getSenderName() +
                                        ", Receiver: " + message.getReceiverName() +
                                        ", Message: " + message.getDescription()));
                    } else {
                        assert sender != null;
                        System.out.println("No messages found for " + sender.getUsername());
                    }
                } else {
                    assert sender != null;
                    System.out.println("No messages found for " + sender.getUsername());
                }
            } else {
                System.out.println("Invalid sender ID");
            }
        } else {
            System.out.println("Invalid input for sender ID");
        }
    }

    private static void addEvent(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter event title:");
        String title = scanner.nextLine().trim();

        System.out.println("Enter event description:");
        String description = scanner.nextLine().trim();

        System.out.println("Enter event date (YYYY-MM-DD HH:MM):");
        Date eventDate;
        try {
            eventDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(scanner.nextLine().trim());
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            return;
        }

        System.out.println("Enter event location:");
        String location = scanner.nextLine().trim();

        Events newEvent = new Events(title, description, eventDate, location);

        Try<Void> addEventAttempt = Try.run(() -> restServerController.addEvent(newEvent));
        addEventAttempt.onSuccess(ignore -> System.out.println("Event added successfully!"))
                .onFailure(error -> System.out.println("Failed to add event: " + error.getMessage()));
    }




    private static void displayEvents(RestServerController restServerController) {
        System.out.println("Showing all events:");
        ResponseEntity<List<Events>> response = restTemplate.exchange(
                "http://localhost:8080/api/events",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Events>>() {});

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Events> events = response.getBody();
            assert events != null;
            events.forEach(System.out::println);
        } else {
            System.out.println("Failed to get events: " + response.getStatusCode());
        }
    }



    private static void removeEvent(RestServerController restServerController, Scanner scanner) {

        System.out.println("Enter ID of the event to remove:");
        Option<Integer> idOption = readInput(scanner);
        idOption.peek(id -> {
            Try.of(() -> restServerController.removeEventByID(Long.valueOf(id)))
                    .onSuccess(ignored -> System.out.println("Event removed successfully!"))
                    .onFailure(error -> System.out.println("Failed to remove event: " + error.getMessage()));
        }).onEmpty(() -> System.out.println("Invalid ID"));
    }


    private static void updateEvent(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter ID of the event to update:");
        Option<Integer> idOption = readInput(scanner);
        idOption.peek(id -> {
            ResponseEntity<Events> response = restTemplate.getForEntity("http://localhost:8080/events/" + id, Events.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Event not found");
                return;
            }

            Events event = response.getBody();

            // Clear the input buffer
            scanner.nextLine();

            System.out.println("Enter new title:");
            String title = scanner.nextLine().trim();

            System.out.println("Enter new description:");
            String description = scanner.nextLine().trim();

            System.out.println("Enter new date (YYYY-MM-DD HH:MM):");
            Date eventDate;
            try {
                eventDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(scanner.nextLine().trim());
            } catch (ParseException e) {
                System.out.println("Invalid date format");
                return;
            }

            System.out.println("Enter new location:");
            String location = scanner.nextLine().trim();

            Events newEvent = new Events(title, description, eventDate, location);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Events> requestUpdate = new HttpEntity<>(newEvent, headers);
            ResponseEntity<String> responseUpdate = restTemplate.exchange("http://localhost:8080/events/" + id, HttpMethod.PUT, requestUpdate, String.class);

            if (responseUpdate.getStatusCode() == HttpStatus.OK) {
                System.out.println("Event updated successfully!");
            } else {
                System.out.println("Failed to update event: " + responseUpdate.getBody());
            }
        }).onEmpty(() -> System.out.println("Invalid ID"));
    }

    private static void displayEventParticipants(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter ID of the event:");
        Option<Integer> idOption = readInput(scanner);
        if (idOption.isDefined()) {
            ResponseEntity<Events> response = restTemplate.getForEntity("http://localhost:8080/api/events/" + idOption.get(), Events.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Invalid event ID");
                return;
            }

            Events event = response.getBody();

            ResponseEntity<Set<User>> responseParticipants = restTemplate.exchange(
                    "http://localhost:8080/api/events/" + idOption.get() + "/participants",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<User>>() {});

            if (responseParticipants.getStatusCode() == HttpStatus.OK) {
                Set<User> participants = responseParticipants.getBody();
                if (!participants.isEmpty()) {
                    System.out.println("Participants of the event '" + event.getEventName() + "':");
                    participants.forEach(System.out::println);
                } else {
                    System.out.println("No participants found for the event '" + event.getEventName() + "'");
                }
            } else {
                System.out.println("Failed to get participants: " + responseParticipants.getStatusCode());
            }
        } else {
            System.out.println("Invalid input for event ID");
        }
    }


    //TODO: Make this method and the one in the controller work correctly... right now the user are not being added
    private static void participateInEvent(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter event ID:");
        Option<Integer> eventIdOption = readInput(scanner);

        System.out.println("Enter your ID:");
        Option<Integer> userIdOption = readInput(scanner);

        if (eventIdOption.isDefined() && userIdOption.isDefined()) {
            ResponseEntity<Events> responseEvent = restTemplate.getForEntity("http://localhost:8080/api/events/" + eventIdOption.get(), Events.class);
            ResponseEntity<User> responseUser = restTemplate.getForEntity("http://localhost:8080/api/users/" + userIdOption.get(), User.class);

            if (responseEvent.getStatusCode() == HttpStatus.NOT_FOUND || responseUser.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Invalid event or user ID");
                return;
            }

            Events event = responseEvent.getBody();
            User user = responseUser.getBody();

            System.out.println("Do you want to:\n1. Participate in the event\n2. Show interest in the event");
            Option<Integer> interestChoiceOption = readInput(scanner);

            if (interestChoiceOption.isDefined()) {
                int choice = interestChoiceOption.get();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<User> requestUpdate = new HttpEntity<>(user, headers);

                if (choice == 1) {
                    ResponseEntity<String> responseParticipate = restTemplate.exchange("http://localhost:8080/api/events/" + eventIdOption.get() + "/participants", HttpMethod.POST, requestUpdate, String.class);
                    if (responseParticipate.getStatusCode() == HttpStatus.OK) {
                        assert event != null;
                        System.out.println("You have successfully participated in the event: " + event.getEventName());
                    } else {
                        System.out.println("Failed to participate in the event: " + responseParticipate.getBody());
                    }
                } else if (choice == 2) {
                    // Assuming there's a similar endpoint for showing interest in the event
                    ResponseEntity<String> responseInterest = restTemplate.exchange("http://localhost:8080/api/events/" + eventIdOption.get() + "/interested", HttpMethod.POST, requestUpdate, String.class);
                    if (responseInterest.getStatusCode() == HttpStatus.OK) {
                        assert event != null;
                        System.out.println("You have shown interest in the event: " + event.getEventName());
                    } else {
                        System.out.println("Failed to show interest in the event: " + responseInterest.getBody());
                    }
                } else {
                    System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid input for event or user ID");
        }
    }

    //TODO -> DISPLAYING ONLY INTERESTED USERS DOES NOT WORK CORRECTLY
    private static void displayUsersInterestedNotParticipating(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter the ID of the event to display interested users not participating:");
        Option<Integer> eventIdOption = readInput(scanner);

        if (eventIdOption.isDefined()) {
            ResponseEntity<Events> response = restTemplate.getForEntity("http://localhost:8080/api/events/" + eventIdOption.get(), Events.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Invalid event ID");
                return;
            }

            Events event = response.getBody();

            ResponseEntity<Set<User>> responseInterested = restTemplate.exchange(
                    "http://localhost:8080/api/events/" + eventIdOption.get() + "/interested",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<User>>() {});

            if (responseInterested.getStatusCode() == HttpStatus.OK) {
                Set<User> interestedButNotParticipating = responseInterested.getBody();
                if (!interestedButNotParticipating.isEmpty()) {
                    System.out.println("Users interested but not participating in the event:");
                    interestedButNotParticipating.forEach(System.out::println);
                } else {
                    System.out.println("No users interested in the event but not participating");
                }
            } else {
                System.out.println("Failed to get interested users: " + responseInterested.getStatusCode());
            }
        } else {
            System.out.println("Invalid input for event ID");
        }
    }
    private static void createPost(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter your ID:");
        Option<Integer> userIdOption = readInput(scanner);

        if (userIdOption.isDefined()) {
            long userId = userIdOption.get();
            ResponseEntity<User> response = restServerController.getUserById(userId);

            if (response.getStatusCode() == HttpStatus.OK) {
                User user = response.getBody();

                if (user != null) {
                    System.out.println("Enter post content:");
                    scanner.nextLine();
                    String content = scanner.nextLine().trim();

                    PostProxy postProxy = new PostProxy(userId, content, new Date());
                    UserPostProxy userPostProxy = new UserPostProxy();
                    userPostProxy.setUser(user);
                    userPostProxy.setPostProxy(postProxy);

                    ResponseEntity<String> postResponse = restServerController.createPostProxy(userPostProxy);

                    if (postResponse.getStatusCode() == HttpStatus.OK) {
                        System.out.println("Post created successfully!");
                    } else {
                        System.out.println("Failed to create post");
                    }
                } else {
                    System.out.println("Invalid user ID");
                }
            } else {
                System.out.println("Failed to get user");
            }
        } else {
            System.out.println("Invalid input for user ID");
        }
    }

    // UI method to add a comment to a post
    private static void addCommentToPost(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter Post ID:");
        Option<Integer> postIdOption = readInput(scanner);

        if (postIdOption.isDefined()) {
            long postId = postIdOption.get();
            ResponseEntity<Post> response = restServerController.getPostById(postId);
            Post post = response.getBody();

            if (post != null) {
                System.out.println("Enter your comment:");
                scanner.nextLine();
                String commentText = scanner.nextLine().trim();

                System.out.println("Enter your user ID:");
                long userId = scanner.nextLong(); // read the user ID from the scanner

                Comment comment = new Comment(postId, commentText, new Date(), userId); // use the actual user ID
                restServerController.addCommentToPost(postId, comment);
                System.out.println("Comment added to the post!");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid Post ID");
        }
    }

    //TODO -> NOT WORKING
    private static void reactToPost(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter Post ID:");
        Option<Integer> postIdOption = readInput(scanner);

        if (postIdOption.isDefined()) {
            long postId = postIdOption.get();
            ResponseEntity<Post> response = restServerController.getPostById(postId);
            Post post = response.getBody();

            if (post != null) {
                System.out.println("Enter reaction (Like, Love, Haha, Wow, Sad, Angry):");
                scanner.nextLine();
                String reactionType = scanner.nextLine().trim();

                ReactionStrategy reactionStrategy = ReactionFactory.createReactionStrategy(reactionType);

                if (reactionStrategy != null) {
                    System.out.println("Enter your user ID:");
                    long userId = scanner.nextLong(); // read the user ID from the scanner

                    reactionStrategy.react(post, userId);
                    System.out.println("Reacted to the post with " + reactionType + "!");
                } else {
                    System.out.println("Invalid reaction type");
                }
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid Post ID");
        }
    }

    // UI method to add a hashtag to a post
//    private static void addHashtagToPost(RestServerController restServerController, Scanner scanner) {
//        System.out.println("Enter Post ID:");
//        Option<Integer> postIdOption = readInput(scanner);
//
//        if (postIdOption.isDefined()) {
//            long postId = postIdOption.get();
//            Post post = restServerController.getPostById(postId);
//
//            if (post != null) {
//                System.out.println("Enter hashtag:");
//                scanner.nextLine();
//                String hashtagText = scanner.nextLine().trim();
//
//                Hashtag hashtag = new Hashtag(hashtagText);
//                restServerController.addHashtagToPost(post, hashtag);
//                System.out.println("Hashtag added to the post!");
//            } else {
//                System.out.println("Post not found.");
//            }
//        } else {
//            System.out.println("Invalid Post ID");
//        }
//    }

    // UI method to remove a hashtag from a post
    private static void removeHashtagFromPost(ServerController serverController, Scanner scanner) {
        System.out.println("Enter Post ID:");
        Option<Integer> postIdOption = readInput(scanner);

        if (postIdOption.isDefined()) {
            long postId = postIdOption.get();
            Post post = serverController.getPostById(postId);

            if (post != null) {
                System.out.println("Enter hashtag to remove:");
                scanner.nextLine();
                String hashtagText = scanner.nextLine().trim();

                Hashtag hashtagToRemove = new Hashtag(hashtagText);
                serverController.removeHashtagFromPost(post, hashtagToRemove);
                System.out.println("Hashtag removed from the post!");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid Post ID");
        }
    }

//     UI method to display all posts
    private static void displayAllPosts(RestServerController restServerController) {
        // Create a new instance of ParameterizedTypeReference for List<Post>
        ParameterizedTypeReference<List<Post>> typeRef = new ParameterizedTypeReference<List<Post>>() {};

        // Send a GET request to the "/posts" endpoint
        ResponseEntity<List<Post>> response = restTemplate.exchange("http://localhost:8080/api/posts", HttpMethod.GET, null, typeRef);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Post> allPosts = response.getBody();

            if (!allPosts.isEmpty()) {
                System.out.println("All Posts:");
                allPosts.forEach(System.out::println);
            } else {
                System.out.println("No posts found.");
            }

            // Check for any new post notifications
            ResponseEntity<Boolean> notificationResponse = restServerController.hasNewPostNotification();
            if (notificationResponse.getStatusCode() == HttpStatus.OK && Boolean.TRUE.equals(notificationResponse.getBody())) {
                System.out.println("You've been notified of a new post!");
                restServerController.clearNewPostNotification(); // Clear the notification
            }
        } else {
            System.out.println("Failed to get posts");
        }
    }


    // UI method to display posts by a specific user
    private static void displayUserPosts(RestServerController restServerController, Scanner scanner) {
        System.out.println("Enter User ID:");
        Option<Integer> userIdOption = readInput(scanner);

        if (userIdOption.isDefined()) {
            long userId = userIdOption.get();
            ResponseEntity<User> userResponse = restServerController.getUserById(userId);

            if (userResponse.getStatusCode() == HttpStatus.OK) {
                User user = userResponse.getBody();
                ResponseEntity<List<Post>> postsResponse = restServerController.getPostsByUser(userId);

                if (postsResponse.getStatusCode() == HttpStatus.OK) {
                    List<Post> userPosts = postsResponse.getBody();

                    assert userPosts != null;
                    if (!userPosts.isEmpty()) {
                        assert user != null;
                        System.out.println("Posts by " + user.getUsername() + ":");
                        userPosts.forEach(System.out::println);
                    } else {
                        assert user != null;
                        System.out.println("No posts found for " + user.getUsername());
                    }
                } else {
                    System.out.println("No posts found for this user.");
                }
            } else {
                System.out.println("User not found.");
            }
        } else {
            System.out.println("Invalid User ID");
        }
    }


    // UI method to add a comment to another user's post
    private static void addCommentToAnotherUserPost(ServerController serverController, Scanner scanner) {
        System.out.println("Enter the Post ID of the user's post you want to comment on:");
        Option<Integer> postIdOption = readInput(scanner);

        if (postIdOption.isDefined()) {
            long postId = postIdOption.get();
            Post post = serverController.getPostById(postId);

            if (post != null) {
                System.out.println("Enter your comment:");
                scanner.nextLine();
                String commentText = scanner.nextLine().trim();

                Comment comment = new Comment(postId, commentText, new Date(), 123); // Replace 123 with the actual user ID
                serverController.addCommentToPost(post, comment);
                System.out.println("Comment added to the post!");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid Post ID");
        }
    }

    // UI method to react to another user's post
    private static void reactToAnotherUserPost(ServerController serverController, Scanner scanner) {
        System.out.println("Enter the Post ID of the user's post you want to react to:");
        Option<Integer> postIdOption = readInput(scanner);

        if (postIdOption.isDefined()) {
            long postId = postIdOption.get();
            Post post = serverController.getPostById(postId);

            if (post != null) {
                System.out.println("Enter reaction (Like, Love, Haha, Wow, Sad, Angry):");
                scanner.nextLine();
                String reactionType = scanner.nextLine().trim();

                Reaction reaction = new Reaction(123, reactionType); // Replace 123 with the actual user ID
                serverController.reactToPost(post, reaction);
                System.out.println("Reacted to the post!");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid Post ID");
        }
    }
}