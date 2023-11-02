//package main.test.testEntities;
//
//import Entities.Misc.IDGenerator;
//import Entities.User.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.junit.Assert.*;
//
//public class TestUser {
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        String username = "testuser";
//        String password = "testpassword";
//        Date birthdate = new Date();
//        String email = "test@example.com";
//        User.Visibility defaultVisibility = User.Visibility.PUBLIC;
//        user = new User(username, password, birthdate, email, defaultVisibility);
//    }
//
//    @Test
//    public void testGetID() {
//        assertEquals(1, user.getID()); // Assuming ID generation starts at 1
//    }
//
//    @Test
//    public void testGetUsername() {
//        assertEquals("testuser", user.getUsername());
//    }
//
//    @Test
//    public void testSetUsername() {
//        user.setUsername("newusername");
//        assertEquals("newusername", user.getUsername());
//    }
//
//    @Test
//    public void testGetPassword() {
//        assertEquals("testpassword", user.getPassword());
//    }
//
//    @Test
//    public void testSetPassword() {
//        user.setPassword("newpassword");
//        assertEquals("newpassword", user.getPassword());
//    }
//
//    @Test
//    public void testGetBirthdate() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date expectedDate = dateFormat.parse(dateFormat.format(new Date()));
//        assertEquals(expectedDate, user.getBirthdate());
//    }
//
//    @Test
//    public void testSetBirthdate() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date newDate = dateFormat.parse("2000-01-01");
//        user.setBirthdate(newDate);
//        assertEquals(newDate, user.getBirthdate());
//    }
//
//    @Test
//    public void testGetEmail() {
//        assertEquals("test@example.com", user.getEmail());
//    }
//
//    @Test
//    public void testSetEmail() {
//        user.setEmail("new@example.com");
//        assertEquals("new@example.com", user.getEmail());
//    }
//
//    @Test
//    public void testGetDefaultVisibility() {
//        assertEquals(User.Visibility.PUBLIC, user.getDefaultVisibility());
//    }
//
//    @Test
//    public void testSetDefaultVisibility() {
//        user.setDefaultVisibility(User.Visibility.PRIVATE);
//        assertEquals(User.Visibility.PRIVATE, user.getDefaultVisibility());
//    }
//
//    @Test
//    public void testEquals() {
//        User otherUser = new User("testuser", "testpassword", new Date(), "test@example.com", User.Visibility.PUBLIC);
//        assertEquals(user, otherUser);
//    }
//
//    @Test
//    public void testNotEquals() {
//        User otherUser = new User("anotheruser", "testpassword", new Date(), "test@example.com", User.Visibility.PUBLIC);
//        assertEquals(false, user.equals(otherUser));
//    }
//}
