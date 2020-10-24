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
    /**
     * Service used to handle registration
     * @param email     String, user's email address
     * @param username  String, user's username
     * @param pw        String, user's password
     * @param type      String, user's account type
     * @return Boolean representing success or failure
     * @throws ClassNotFoundException
     */
    public boolean register(String email,String username, String pw, String type) {
        User nuser = new User(email,username,pw,type);
        return nuser.persistUser();
    }
}

