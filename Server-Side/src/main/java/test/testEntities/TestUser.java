package test.testEntities;

import Entities.Misc.IDGenerator;
import Entities.User.Administrator;
import Entities.User.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestUser {
    private User user;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        String username = "testuser";
        String password = "testpassword";
        Date birthdate = new Date();
        String email = "test@example.com";
        User.Visibility defaultVisibility = User.Visibility.PUBLIC;
        user = new User(username, password, birthdate, email, defaultVisibility);
    }

    @Test
    public void testConstructor() {
        User userCopy = new User(user);
        assertEquals(userCopy, user);
        assertEquals(userCopy.getUsername(), user.getUsername());
        assertEquals(userCopy.getPassword(), user.getPassword());
        assertEquals(userCopy.getBirthdate(), user.getBirthdate());
        assertEquals(userCopy.getDefaultVisibility(), user.getDefaultVisibility());
    }

    @Test
    public void testGetID() {
        assertEquals(1, user.getUserID());
    }

    @Test
    public void testGetUsername() {
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newusername");
        assertEquals("newusername", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testGetBirthdate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedDate = dateFormat.parse(dateFormat.format(new Date()));

        // Extract day, month, and year components of the dates
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(expectedDate);
        int expectedDay = expectedCalendar.get(Calendar.DAY_OF_MONTH);
        int expectedMonth = expectedCalendar.get(Calendar.MONTH);
        int expectedYear = expectedCalendar.get(Calendar.YEAR);

        Calendar userCalendar = Calendar.getInstance();
        userCalendar.setTime(user.getBirthdate());
        int userDay = userCalendar.get(Calendar.DAY_OF_MONTH);
        int userMonth = userCalendar.get(Calendar.MONTH);
        int userYear = userCalendar.get(Calendar.YEAR);

        // Compare day, month, and year components
        assertEquals(expectedYear, userYear);
        assertEquals(expectedMonth, userMonth);
        assertEquals(expectedDay, userDay);
    }


    @Test
    public void testSetBirthdate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = dateFormat.parse("2000-01-01");
        user.setBirthdate(newDate);
        assertEquals(newDate, user.getBirthdate());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    public void testGetDefaultVisibility() {
        assertEquals(User.Visibility.PUBLIC, user.getDefaultVisibility());
    }

    @Test
    public void testSetDefaultVisibility() {
        user.setDefaultVisibility(User.Visibility.PRIVATE);
        assertEquals(User.Visibility.PRIVATE, user.getDefaultVisibility());
    }

    @Test
    public void testEquals() {
        assertEquals(user, user);
    }

    @Test
    public void testNotEquals() {
        User otherUser = new User("anotheruser", "testpassword", new Date(), "test@example.com", User.Visibility.PUBLIC);
        assertNotEquals(user, otherUser);
    }

    @Test
    public void createUserAndVerifyFields() {
        // Create a user
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setBirthdate(new Date());
        user.setEmail("test@example.com");
        user.setDefaultVisibility(User.Visibility.PUBLIC);

        // Verify user fields
        Assertions.assertEquals("testUser", user.getUsername());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertNotNull(user.getBirthdate());
        Assertions.assertEquals("test@example.com", user.getEmail());
        Assertions.assertEquals(User.Visibility.PUBLIC, user.getDefaultVisibility());
    }

    @Test
    public void createAdministratorAndVerifyPermission() {
        // Create an administrator
        Administrator admin = new Administrator();
        admin.setUsername("adminUser");
        admin.setPassword("adminPassword");
        admin.setBirthdate(new Date());
        admin.setEmail("admin@example.com");
        admin.setDefaultVisibility(User.Visibility.PRIVATE);

        // Verify admin fields and permission
        Assertions.assertEquals("adminUser", admin.getUsername());
        Assertions.assertEquals("adminPassword", admin.getPassword());
        Assertions.assertNotNull(admin.getBirthdate());
        Assertions.assertEquals("admin@example.com", admin.getEmail());
        Assertions.assertEquals(User.Visibility.PRIVATE, admin.getDefaultVisibility());
        Assertions.assertEquals(User.Permission.ADMIN, admin.getPermission());
    }
}
