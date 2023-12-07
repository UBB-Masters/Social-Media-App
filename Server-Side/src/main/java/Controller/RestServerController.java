package Controller;

import Controller.Services.*;
import Entities.Events.Events;
import Entities.Message.MessageFactory;
import Entities.User.User;
import Proxy.PostProxy;
import Entities.Post.Post;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestServerController {

    private final UserService userService;
    private final EventService eventService;
    private final PostService postService;
    private final MessageService messageService;

    @Autowired

    public RestServerController(UserService userService,
                                EventService eventService,
                                PostService postService,
                                MessageService messageService) {
        this.userService = userService;
        this.eventService = eventService;
        this.postService = postService;
        this.messageService = messageService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        User oldUser = userService.getUserById(id);
        if(oldUser != null) {
            userService.updateUser(oldUser, newUser);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if(user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUserById(@PathVariable Long id) {
        User user = userService.removeUserById(id);
        if (user != null) {
            return ResponseEntity.ok("User removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Update specific fields of a user
    @PatchMapping("/users/{id}/username")
    public ResponseEntity<String> updateUserUsername(@PathVariable Long id, @RequestBody String newUsername) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.updateUserUsername(user, newUsername);
            return ResponseEntity.ok("Username updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PatchMapping("/users/{id}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.updateUserPassword(user, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PatchMapping("/users/{id}/birthdate")
    public ResponseEntity<String> updateUserBirthday(@PathVariable Long id, @RequestBody Date newBirthday) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.updateUserBirthday(user, newBirthday);
            return ResponseEntity.ok("Birthday updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PatchMapping("/users/{id}/email")
    public ResponseEntity<String> updateUserEmail(@PathVariable Long id, @RequestBody String newEmail) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.updateUserEmail(user, newEmail);
            return ResponseEntity.ok("Email updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest)  {
        messageService.sendMessage(messageRequest.getSender(), messageRequest.getReceiver(), messageRequest.getMessage());
        return ResponseEntity.ok("Message sent successfully");
    }
    @GetMapping("/messages/{senderId}")
    public ResponseEntity<List<MessageFactory>> getSentMessages(@PathVariable Long senderId) {
        User sender = userService.getUserById(senderId);
        if(sender != null) {
            List<MessageFactory> sentMessages = messageService.getSentMessages(sender);
            return ResponseEntity.ok(sentMessages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestBody Events event) {
        eventService.addEvent(event);
        return ResponseEntity.ok("Event added successfully");
    }

    @DeleteMapping("/events")
    public ResponseEntity<String> removeEvent(@RequestBody Events event) {
        eventService.removeEvent(event);
        return ResponseEntity.ok("Event removed successfully");
    }

    @GetMapping("/events/{id}/participants")
    public ResponseEntity<Set<User>> getEventParticipants(@PathVariable Long id) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            return ResponseEntity.ok(eventService.getEventParticipants(event));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/events")
    @CrossOrigin
    public ResponseEntity<List<Events>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable Long id) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/events/{id}/participants")
    public ResponseEntity<String> addParticipantToEvent(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.addParticipantToEvent(event, user);
            return ResponseEntity.ok("Participant added to event");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @DeleteMapping("/events/{id}/participants")
    public ResponseEntity<String> removeParticipantFromEvent(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.removeParticipantFromEvent(event, user);
            return ResponseEntity.ok("Participant removed from event");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @PostMapping("/events/{id}/interested")
    public ResponseEntity<String> addInterestedUserToEvent(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.addInterestedUserToEvent(event, user);
            return ResponseEntity.ok("User added to interested list");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @DeleteMapping("/events/{id}/interested")
    public ResponseEntity<String> removeInterestedUserFromEvent(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.removeInterestedUserFromEvent(event, user);
            return ResponseEntity.ok("User removed from interested list");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> removeEventByID(@PathVariable Long id) {
        Events event = eventService.removeEventByID(id);
        if(event != null) {
            return ResponseEntity.ok("Event removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Events newEvent) {
        Events oldEvent = eventService.getEventById(id);
        if(oldEvent != null) {
            eventService.updateEvent(oldEvent, newEvent);
            return ResponseEntity.ok("Event updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @PostMapping("/events/{id}/join")
    public ResponseEntity<String> joinEvent(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.joinEvent(user, event);
            return ResponseEntity.ok("User joined event");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @PostMapping("/events/{id}/interest")
    public ResponseEntity<String> showInterest(@PathVariable Long id, @RequestBody User user) {
        Events event = eventService.getEventById(id);
        if(event != null) {
            eventService.showInterest(user, event);
            return ResponseEntity.ok("User showed interest in event");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @GetMapping("/events/{id}/interested")
    public ResponseEntity<Set<User>> getUsersInterestedInEvent(@PathVariable Long id) {
        Events event = eventService.getEventById(id);
        if (event != null) {
            // Explicitly initialize the interestedUsers collection
            Hibernate.initialize(event.getInterestedUsers());
            return ResponseEntity.ok(eventService.getUsersInterestedInEvent(event));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody User user, @RequestBody String content) {
        postService.createPost(user, content);
        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/posts/proxy")
    public ResponseEntity<String> createPostProxy(@RequestBody UserPostProxy userPostProxy) {
        postService.createPostProxy(userPostProxy.getUser(), userPostProxy.getPostProxy());
        return ResponseEntity.ok("Post created successfully using proxy");
    }

    @GetMapping("/posts/notifications")
    public ResponseEntity<Boolean> hasNewPostNotification() {
        boolean hasNotification = postService.hasNewPostNotification();
        return ResponseEntity.ok(hasNotification);
    }

    @DeleteMapping("/posts/notifications")
    public ResponseEntity<String> clearNewPostNotification() {
        postService.clearNewPostNotification();
        return ResponseEntity.ok("Post notification cleared");
    }


}



