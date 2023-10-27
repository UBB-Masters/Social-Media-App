package Entities.Chanels;

import Entities.Chanels.ChanelTypes.DirectChanel;
import Entities.Chanels.ChanelTypes.GroupChat;
import Entities.Exceptions.ChanelException;
import Entities.Exceptions.MessageException;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Misc.IDGenerator;
import Entities.User.User;

public class ChanelFactory {

    protected final long ID;

    public ChanelFactory() {
        this.ID = IDGenerator.generateID(ChanelFactory.class);
    }

    public enum ChanelType {
        DIRECT, GROUP
    }

    // Factory Method to create specific message types based on the MessageType enum
    public static ChanelFactory createMessage(ChanelType chanelType) throws ChanelException {
        return switch (chanelType) {
            case DIRECT -> new DirectChanel();
            case GROUP -> new GroupChat();
            default -> throw new ChanelException("Invalid Chanel Type provided");
        };
    }

    public long getID() {
        return this.ID;
    }
}
