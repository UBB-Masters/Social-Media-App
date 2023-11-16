package main.test.testEntities.TestUsers;

import Entities.User.Administrator;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAdministrator {
    private Administrator admin;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create an Administrator
        admin = new Administrator("adminUser", "adminPassword", new Date(), "admin@example.com", User.Visibility.PUBLIC);

        // Create a regular User
        user = new User("testUser", "testPassword", new Date(), "test@example.com", User.Visibility.PUBLIC);
    }

    @Test
    public void testBanUser() {
        admin.banUser(user);
        assertEquals(User.UserStatus.BANNED, user.getUserStatus());
    }

    @Test
    public void testUnbanUser() {
        user.setUserStatus(User.UserStatus.BANNED); // Simulate a banned user
        admin.unbanUser(user);
        assertEquals(User.UserStatus.ACTIVE, user.getUserStatus());
    }
}