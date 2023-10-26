package Entities.User;

import Entities.Misc.Email;

import java.util.Date;

public class Administrator extends User{
    public Administrator(int id, String username, String password, Date birthdate, Email email) {
        super(id, username, password, birthdate, email);
    }
}
