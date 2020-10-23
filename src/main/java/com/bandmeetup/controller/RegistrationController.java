package com.bandmeetup.controller;

import com.bandmeetup.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Controller for the Registration Page
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/19/20
 * Last Edit: 10/19/20
 */
public class RegistrationController {
    @Autowired
    RegistrationService service;
    /**
     * Handles GET requests to the register page
     * @return String redirect to Register
     */
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String displayRegistrationPage(){
        // TODO: Create a register page
        return "register";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String handleRegister(@RequestParam(name="uname") String username, @RequestParam(name="password")String password, @RequestParam(name="accountT")String type){
        return "register";
    }
}
