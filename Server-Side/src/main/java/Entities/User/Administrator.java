package Entities.User;

import Entities.Misc.Email;

import java.util.Date;

public class Administrator extends User{
    public Administrator(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        super(username, password, birthdate, email, defaultVisibility);
        this.permission = Permission.ADMIN;
    }

    // Create a method to ban a user account
    public void banUser(User user) {
        user.setUserStatus(UserStatus.BANNED);
    }

    // Create a method to unban a user account
    public void unbanUser(User user) {
        user.setUserStatus(UserStatus.ACTIVE);
    }
}
