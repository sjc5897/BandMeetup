package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenueManager;
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
    @Autowired
    Dao<Musician> musicianDao;
    @Autowired
    Dao<VenueManager> venueManagerDao;

    public User getUser(String user_email){
        Optional<User> user = userDao.get(user_email);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }

    public Musician getMusician(String user_email){
        Optional<Musician> musician = musicianDao.get(user_email);
        if(musician.isEmpty()){
            return null;
        }
        return musician.get();
    }

    public VenueManager getVenueManager(String user_email){
        Optional<VenueManager> venueManager = venueManagerDao.get(user_email);
        if(venueManager.isEmpty()){
            return null;
        }
        return venueManager.get();
    }
    public boolean updateMusician(Musician muse){
        return musicianDao.update(muse);
    }
    public boolean updateVenueManager(VenueManager man){
        return venueManagerDao.update(man);
    }
}
