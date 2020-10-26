package com.bandmeetup.services;

import com.bandmeetup.DAO.UserDAO;
import com.bandmeetup.SQLiteConfig;
import com.bandmeetup.model.User;
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
    UserDAO uDao = new UserDAO();
    /**
     * The method for validating a user, currently stubbed
     * @param uid: String of the inputted UserID
     * @param password: String of the inputted password
     * @return Boolean of if the user is valid
     */
    public boolean validateUser(String uid, String password ) {
        Optional<User> user = uDao.get(uid);
        if (user.isEmpty()) {
            return false;
        }
        return user.get().authenticate(password);
    }
}
