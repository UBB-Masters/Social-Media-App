package Entities.User;

import Utils.PasswordHasher;
import jakarta.persistence.Entity;

@Entity
public class HashedPasswordDecorator extends User {
    private String hashedPassword;

    public HashedPasswordDecorator() {
    }

    public HashedPasswordDecorator(User user) {
        super(user);
        this.hashedPassword = PasswordHasher.hashPassword(user.getPassword());
    }

    @Override
    public String getHashedPassword() {
        return hashedPassword;
    }
}

