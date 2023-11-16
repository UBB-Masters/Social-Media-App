package Events;

import Entities.Misc.IDGenerator;
import Entities.User.User;
import Observer.Observable;
import Observer.Observer;

import java.util.*;

public class Events implements Observable {

    private final long ID;
    private String eventName;
    private String eventDescription;
    private Set<User> participants;
    private Set<User> interestedUsers;

    private Date eventDate;

    private String eventLocation;

    private final List<Observer> observers = new ArrayList<>();

    public Events(String eventName, String eventDescription, Date eventDate, String eventLocation) {
        this.ID = IDGenerator.generateID(Events.class);
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.participants = new HashSet<>();
        this.interestedUsers = new HashSet<>();
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public long getID() {
        return ID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String newEventName) {
        this.eventName = newEventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String newEventDescription) {
        this.eventDescription = newEventDescription;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> newParticipants) {
        this.participants = newParticipants;
    }

    public Set<User> getInterestedUsers() {
        return interestedUsers;
    }

    public void setInterestedUsers(Set<User> newInterestedUsers) {
        this.interestedUsers = newInterestedUsers;
    }

    public void addParticipant(User user) {
        participants.add(user);
        interestedUsers.remove(user);
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }


    public void addInterestedUser(User user) {
        if (!participants.contains(user)) {
            interestedUsers.add(user);
        }
    }

    @Override
    public String toString() {
        return "Events{" +
                "ID=" + ID +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", participants=" + participants +
                ", interestedUsers=" + interestedUsers +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                '}';
    }

    public void removeInterestedUser(User user) {
        interestedUsers.remove(user);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(this);
        }
    }


}
