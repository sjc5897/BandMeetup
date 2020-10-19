package com.bandmeetup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Controller for the Registration Page
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/19/20
 * Last Edit: 10/19/20
 */
public class RegistrationController {
    /**
     * Handles GET requests to the register page
     * @return String redirect to Register
     */
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String displayRegistrationPage(){
        // TODO: Create a register page
        return "register";
    }
    // TODO: Create a method to handle submission of registration form AND a service to register users.

}
