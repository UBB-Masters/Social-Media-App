package Persistence;

import Entities.Exceptions.DataBaseException;

import java.util.Set;

abstract class InMemoryRepositoryTemplate<T> {
    private Set<T> entities;

    public InMemoryRepositoryTemplate(Set<T> entities) {

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
