package Persistence;

import Entities.Exceptions.DataBaseException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Set;

@NoRepositoryBean
abstract class RepositoryTemplate<T, ID extends Serializable> implements JpaRepository<T, ID> {
    private Set<T> entities;

    public RepositoryTemplate(Set<T> entities) {

        this.entities = entities;
    }

    public Set<T> getEntities() {
        return entities;
    }

    public void setEntities(Set<T> entities) {
        this.entities = entities;
    }

    protected void addEntity(T object) throws DataBaseException {
        if (!entities.add(object)) {
            throw new DataBaseException("Object already exist in the repository");
        }
    }

    protected void removeEntity(T object) throws DataBaseException {
        if (!entities.remove(object)) {
            throw new DataBaseException("Object does not exist in the repository");
        }
    }

    protected void updateEntity(T oldObject, T newObject) throws DataBaseException {
        try {
            entities.remove(oldObject);
            entities.add(newObject);
        } catch (Exception exception) {
            throw new DataBaseException("An error occurred while updating the entity.", exception);
        }
    }
}