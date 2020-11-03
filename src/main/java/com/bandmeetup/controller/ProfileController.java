package com.bandmeetup.controller;

import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenueManager;
import com.bandmeetup.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * This is the LoginController, handles GETs and POSTs to profile
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/28/20
 * Last Edit: 11/3/20
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
    @RequestMapping(value="/profile/{email}", method = RequestMethod.GET)
    public String displayLoginPage(@PathVariable("email")String email,Model model){


        email += ".com";
        Optional<User> userO = service.getUser(email);
        if(userO.isEmpty()){
            return "redirect:/cerror/profile_not_found";
        }
        else {
            User user = userO.get();
            if (user.getUserType().equals("Musician")) {
                Optional<Musician> musicianO= service.getMusician(email);
                if(musicianO.isEmpty()){
                    return "redirect:/cerror/profile_not_found";
                }
                else{
                    Musician profile = musicianO.get();
                    model.addAttribute("Profile", profile);
                }
            }
            else if (user.getUserType().equals("VenueManager")) {
                Optional<VenueManager> venueManagerO = service.getVenueManager(email);
                if(venueManagerO.isEmpty()){
                    return "redirect:/cerror/profile_not_found";
                }
                else{
                    VenueManager profile = venueManagerO.get();
                    model.addAttribute("Profile", profile);
                }
            }
        }
        return "profile";
    }
}
