package Persistence;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;

import javax.xml.crypto.Data;
import java.util.Set;

public class InMemoryUserRepository extends InMemoryRepositoryTemplate<User> {

    public InMemoryUserRepository(Set<User> entities) {
        super(entities);
    }

    public User findById(int id) {
        return getEntities().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User findByUsername(String username) {
        return getEntities().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void add(User user) throws DataBaseException {
        addEntity(user);
    }

    public void remove(User user) throws DataBaseException {
        removeEntity(user);
    }

    public void update(User oldUser, User newUser) throws DataBaseException {
        updateEntity(oldUser, newUser);
    }
}