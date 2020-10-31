package com.bandmeetup.controller;

import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenuManager;
import com.bandmeetup.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the LoginController, handles GETs and POSTs to profile
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/28/20
 * Last Edit: 10/31/20: SPOOKY
 */
@Controller
public class ProfileController {
    @Autowired
    ProfileService service;
    /**
     * The method that handles Profile page requests
     * This will most likely need some sort of ID passed to query the correct user and generate the profile.
     * @return String Login, which is used to represent the Profile page
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String displayLoginPage(@ModelAttribute("email") String email){
        User user = service.getUser(email);
        if (user.getUserType().equals("Musician")){
            Musician profile = service.getMusician(email);
        }
        else if (user.getUserType().equals("VenueManager")){
            VenuManager profile = new VenuManager("a","a","a","a","a","a");
        }
        return "profile";
    }
}
