package main.test.testEntities;

import Entities.Misc.IDGenerator;
import Entities.User.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestUser {
    private User user;

    @Before
    public void setUp() {
        IDGenerator.resetCounters();
        user = new User("john_doe", "password123", new Date(), "john@example.com");
    }

    @Test
    public void testGetID() {
        System.out.println(user.getID());
        assertEquals(1, user.getID());
    }

    @Test
    public void testGetUsername() {
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("jane_doe");
        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("new_password");
        assertEquals("new_password", user.getPassword());
    }

    @Test
    public void testGetBirthdate() {
        assertNotNull(user.getBirthdate());
    }

    @Test
    public void testSetBirthdate() {
        Date newBirthdate = new Date();
        user.setBirthdate(newBirthdate);
        assertEquals(newBirthdate, user.getBirthdate());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    public void testInvalidEmail() {
        try {
            user.setEmail("invalid_email"); // Should throw an IllegalArgumentException
            fail("Setting an invalid email should throw an exception");
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
}
