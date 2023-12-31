package Entities.Exceptions;

public class MessageException extends RuntimeException {
    public MessageException() {
        super("The message provided is invalid");
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}