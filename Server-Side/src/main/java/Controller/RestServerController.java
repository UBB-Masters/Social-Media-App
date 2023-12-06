package Controller;
import Controller.Services.EventService;
import Controller.Services.MessageService;
import Controller.Services.PostService;
import Controller.Services.UserService;
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
}
