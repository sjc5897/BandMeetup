package com.bandmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the LoginController, handles GETs and POSTs to profile
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/28/20
 * Last Edit: 10/28/20
 */
@Controller
public class ProfileController {
    /**
     * The method that handles Profile page requests
     * This will most likely need some sort of ID passed to query the correct user and generate the profile.
     * @return String Login, which is used to represent the Profile page
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String displayLoginPage(){
        return "profile";
    }
}
