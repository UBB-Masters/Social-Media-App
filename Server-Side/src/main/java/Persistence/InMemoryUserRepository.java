package Persistence;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;

import java.util.HashSet;

public class InMemoryUserRepository extends InMemoryRepositoryTemplate<User> {

    public InMemoryUserRepository() {
        super(new HashSet<>());
    }

    public User findById(int id) {
        return getEntities().stream()
                .filter(user -> user.getID() == id)
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

    public User removeUserById(int id) throws DataBaseException {
        User userToRemove = findById(id);
        if (userToRemove != null) {
            try {
                removeEntity(userToRemove);
            } catch (DataBaseException e) {
                throw e;
            }
        } else {
            throw new DataBaseException("User not found");
        }
        return userToRemove;
    }

}