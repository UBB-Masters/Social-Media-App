package Entities.User;

import Entities.Misc.Email;

import java.util.Date;

public class Administrator extends User{
    public Administrator(String username, String password, Date birthdate, Email email) {
        super(username, password, birthdate, email);
    }
}