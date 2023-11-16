package main.test.testEntities;

import Entities.User.ProfilePicture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestProfilePicture {

    @Test
    public void createProfilePictureAndVerifyFields() {
        // Create a profile picture
        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setPicture("test.jpg");

        // Verify profile picture fields
        Assertions.assertEquals("test.jpg", profilePicture.getPicture());
    }

    @Test
    public void createProfilePictureWithDefaultConstructor() {
        // Create a profile picture using the default constructor
        ProfilePicture profilePicture = new ProfilePicture();

        // Verify profile picture default value
        Assertions.assertEquals("default", profilePicture.getPicture());
    }

    @Test
    public void setAndGetPictureID() {
        // Create a profile picture
        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setPictureID(123);

        // Verify picture ID
        Assertions.assertEquals(123, profilePicture.getPictureID());
    }
}
