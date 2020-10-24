package com.bandmeetup.services;

import com.bandmeetup.model.User;

/**
 * Service for registration
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/24/20
 * Last Edit: 10/24/20
 */
public class RegistrationService {
    public boolean register(String email,String username, String pw, String type) throws ClassNotFoundException {
        User nuser = new User(email,username,pw,type);
        boolean success = nuser.persistUser();
        return success;
    }
}

