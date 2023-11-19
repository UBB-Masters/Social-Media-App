package Entities.User;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "admin")
public class Administrator extends User {

    public Administrator(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        super(username, password, birthdate, email, defaultVisibility);
        this.setPermission(Permission.ADMIN);
    }

    public Administrator() {
        super();
        this.setPermission(Permission.ADMIN);
    }

    public void banUser(User user) {
        user.setUserStatus(UserStatus.BANNED);
    }

    public void unbanUser(User user) {
        user.setUserStatus(UserStatus.ACTIVE);
    }
}
