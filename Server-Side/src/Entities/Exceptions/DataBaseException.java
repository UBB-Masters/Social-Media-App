package Entities.Exceptions;

public class DataBaseException extends Exception {
    public DataBaseException() {
        super("A database-related exception occurred.");
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}