package Entities.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
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
