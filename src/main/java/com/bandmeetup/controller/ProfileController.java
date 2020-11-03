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

import javax.swing.*;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    public void addProfile(String email,Model model){
        User user = service.getUser(email);
        if (user == null){
            return;
        }
        else if (user.getUserType().equals("Musician")) {
            Musician profile = service.getMusician(email);
            model.addAttribute("Profile", profile);
        }
        else if(user.getUserType().equals("VenueManager")){
            VenueManager profile = service.getVenueManager(email);
            model.addAttribute("Profile", profile);
        }
    }
    /**
     * The method that handles Profile page requests
     * This will most likely need some sort of ID passed to query the correct user and generate the profile.
     * @return String Login, which is used to represent the Profile page
     */
    @RequestMapping(value="/edit/{email}", method = GET)
    public String getEditPage(@PathVariable("email")String email, Model model){
        email += ".com";
        addProfile(email,model);

        if(model.getAttribute("Profile") == null){
            return "redirect:/cerror/profile_not_found";
        }
        return "edit";
    }


    @RequestMapping(value="/profile/{email}", method = RequestMethod.GET)
    public String displayLoginPage(@PathVariable("email")String email,Model model) {

        email += ".com";
        addProfile(email, model);

        if (model.getAttribute("Profile") == null) {
            return "redirect:/cerror/profile_not_found";
        }
        return "profile";
    }
}
