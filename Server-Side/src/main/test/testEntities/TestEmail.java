package main.test.testEntities;

import Entities.Misc.Email;
import org.junit.Assert;
import org.junit.Test;

public class TestEmail {
    @Test
    public void testValidEmail() {
        try {
            Email validEmail = new Email("user@example.com");
            Assert.assertEquals("user@example.com", validEmail.getAddress());
        } catch (IllegalArgumentException e) {
            Assert.fail("Valid email should not throw an exception");
        }
    }

    @Test
    public void testInvalidEmail() {
        try {
            Email invalidEmail = new Email("invalid_email");
            Assert.fail("Invalid email should throw an exception");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Invalid email address", e.getMessage());
        }
    }
}
