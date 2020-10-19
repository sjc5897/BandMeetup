package com.bandmeetup.services;

import org.springframework.stereotype.Service;

/**
 * The service for Login, this assists the controller in authentication
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/17/20
 * Last Edit: 10/19/20
 */

@Service
public class LoginService {

    /**
     * The method for validating a user, currently stubbed
     * @param uid: String of the inputted UserID
     * @param password: String of the inputted password
     * @return Boolean of if the user is valid
     */
    public boolean validateUser(String uid, String password ){
        // TODO: Implement validation for real
        return uid.equals("Stephen") && password.equals("abc123");
    }
}
