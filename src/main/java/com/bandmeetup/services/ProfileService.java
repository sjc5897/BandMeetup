package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is a service for handleing the profiles, It currently gets the requested profile info
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/31/20: SPOOPY
 * Last Edit: 10/31/20
 */
@Service
public class ProfileService {
    @Autowired
    Dao<User> userDao;
    Dao<Musician> musicianDao;

    public User getUser(String user_email){
        Optional<User> user = userDao.get(user_email);
        return user.get();
    }

    public Musician getMusician(String user_email){
        Optional<Musician> musician = musicianDao.get(user_email);
        return musician.get();
    }

}
