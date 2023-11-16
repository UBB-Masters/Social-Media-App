package Entities.Exceptions;

public class ChanelException extends RuntimeException {
    public ChanelException() {
        super("A chanel-related exception occurred.");
    }

    public ChanelException(String message) {
        super(message);
    }

    public ChanelException(String message, Throwable cause) {
        super(message, cause);
    }
}
