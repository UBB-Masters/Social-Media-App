package Test;

import Entities.Misc.Email;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestEmail {
    @Test
    public void testValidEmail() {
        try {
            Email validEmail = new Email("user@example.com");
            assertEquals("user@example.com", validEmail.getEmail());
        } catch (IllegalArgumentException e) {
            fail("Valid email should not throw an exception");
        }
    }

    @Test
    public void testInvalidEmail() {
        try {
            Email invalidEmail = new Email("invalid_email");
            fail("Invalid email should throw an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid email address", e.getMessage());
        }
    }
}
