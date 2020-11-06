package com.bandmeetup.services;

import com.bandmeetup.DAO.Dao;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenueManager;
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
    @Autowired
    private Dao<Musician> musicianDao;
    @Autowired
    private Dao<VenueManager> venueManagerDao;

    /**
     * Service used to handle registration
     * @param email     String, user's email address
     * @param pw        String, user's password
     * @param type      String, user's account type
     * @return Boolean representing success or failure
     * @throws ClassNotFoundException
     */
    public String register(String email, String name, String pw, String type) {
        if (type.equals("Musician")){
            Musician n_user = new Musician(email,name,pw,type,"Lookingtojoinaband","","","","");
            String resp = musicianDao.save(n_user);
            if(resp.contains("Error: ")){
                return resp;
            }
            return userDao.save(n_user);
        }
        if (type.equals("VenueManager")){
            VenueManager n_user = new VenueManager(email,name,pw,type,"","");
            String resp = venueManagerDao.save(n_user);
            if(resp.contains("Error: ")){
                return resp;
            }
            return userDao.save(n_user);
        }
        return "Error: ";
    }

}



