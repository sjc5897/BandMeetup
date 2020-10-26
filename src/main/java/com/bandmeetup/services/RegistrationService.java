package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.DAO.UserDAO;
import com.bandmeetup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private Dao<User> userDao;
    /**
     * Service used to handle registration
     * @param email     String, user's email address
     * @param pw        String, user's password
     * @param type      String, user's account type
     * @return Boolean representing success or failure
     * @throws ClassNotFoundException
     */
    public String register(String email, String pw, String type) {
        User n_user = new User(email,pw,type);
        return userDao.save(n_user);
    }
}

