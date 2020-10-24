package com.bandmeetup.services;

import com.bandmeetup.model.User;
import org.springframework.stereotype.Service;

/**
 * Service for registration
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/24/20
 * Last Edit: 10/24/20
 */
@Service
public class RegistrationService {
    public boolean register(String email,String username, String pw, String type) throws ClassNotFoundException {
        User nuser = new User(email,username,pw,type);
        System.out.print("per");
        return nuser.persistUser();
    }
}

