package Entities.Exceptions;

public class InvalidMessageException extends Exception {
    public InvalidMessageException() {
        super("The message provided is invalid");
    }

    public InvalidMessageException(String message) {
        super(message);
    }
}