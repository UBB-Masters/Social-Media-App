package Controller;
import Controller.Services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import Entities.Events.Events;
import Entities.Exceptions.DataBaseException;
import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
import Entities.Post.Comment;
import Entities.Post.Post;
import Entities.Post.Hashtag;
import Entities.User.User;
import Entities.Reaction.Reaction;
import Proxy.PostProxy;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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


}



