package RudimentaryDataBase;

import Entities.Exceptions.DataBaseException;

import java.util.Set;

abstract class InMemoryRepositoryTemplate {
    private Set<Object> entities;

    public InMemoryRepositoryTemplate(Set<Object> entities) {
        this.entities = entities;
    }

    public Set<Object> getEntities() {
        return entities;
    }

    public void setEntities(Set<Object> entities) {
        this.entities = entities;
    }

    private void addEntity(Object object) throws DataBaseException {
        if (!entities.add(object)) {
            throw new DataBaseException("Object already exist in the repository");
        }
    }

    private void removeEntity(Object object) throws DataBaseException {
        if (!entities.remove(object)) {
            throw new DataBaseException("Object does not exist in the repository");
        }
    }

    private void updateEntity(Object oldObject, Object newObject) throws DataBaseException {
        try {
            entities.remove(oldObject);
            entities.add(newObject);
        } catch (Exception exception) {
            throw new DataBaseException("An error occurred while updating the entity.", exception);
        }
    }
}
