package Persistence;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;

import java.util.HashSet;

public class InMemoryUserRepository extends InMemoryRepositoryTemplate<User> {

    private static InMemoryUserRepository instance = null;

    private InMemoryUserRepository() {
        super(new HashSet<>());
    }

    public static InMemoryUserRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryUserRepository();
        }
        return instance;
    }

    public User findById(int id) {
        return getEntities().stream()
                .filter(user -> user.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public User findById(long id) {
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
            removeEntity(userToRemove);
        } else {
            throw new DataBaseException("User not found");
        }
        return userToRemove;
    }

    public User removeUserById(long id) throws DataBaseException {
        User userToRemove = findById(id);
        if (userToRemove != null) {
            removeEntity(userToRemove);
        } else {
            throw new DataBaseException("User not found");
        }
        return userToRemove;
    }

}