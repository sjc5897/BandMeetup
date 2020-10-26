package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.DAO.UserDAO;
import com.bandmeetup.SQLiteConfig;
import com.bandmeetup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Autowired
    private Dao<User> userDao;
    /**
     * The method for validating a user, currently stubbed
     * @param uid: String of the inputted UserID
     * @param password: String of the inputted password
     * @return Boolean of if the user is valid
     */
    public boolean validateUser(String uid, String password ) {
        // Looks for sent uid in the user database
        Optional<User> user = userDao.get(uid);
        // We get empty if no user found
        if (user.isEmpty()) {
            return false;
        }
        // Otherwise we want to authenticate user
        return user.get().authenticate(password);
    }
}
