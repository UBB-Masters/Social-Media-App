package Persistence.InMemoryRepositories;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class InMemoryUserInMemoryRepository extends InMemoryRepositoryTemplate<User> {

    private static InMemoryUserInMemoryRepository instance = null;

    @Autowired
    private InMemoryUserInMemoryRepository() {
        super(new HashSet<>());
    }

    public static InMemoryUserInMemoryRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryUserInMemoryRepository();
        }
        return instance;
    }

    public User findById(int id) {
        return getEntities().stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    public User findById(long id) {
        return getEntities().stream()
                .filter(user -> user.getUserID() == id)
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