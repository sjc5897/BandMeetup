package com.bandmeetup.controller;

import com.bandmeetup.model.Musician;
import com.bandmeetup.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.bandmeetup.security.Hasher.hashPass;

/**
 * The Controller for the Registration Page
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/19/20
 * Last Edit: 10/24/20
 */
@Controller
public class RegistrationController {
    @Autowired
    RegistrationService service;

    /**
     * Handles GET requests to the register page
     *
     * @return String redirect to Register
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistrationPage() {
        return "register";
    }

    /**
     * Handles the Post Requests to /register attempts to create and persist a new user
     * @param email     String, user's email address
     * @param pw        String, user's password
     * @param type      String, user's account type
     * @return String redirect to relevant page
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegister(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "full_name") String name,
                                 @RequestParam(name = "pw") String pw,
                                 @RequestParam(name = "accountT") String type, Model model) {

        pw = hashPass(pw);
        String resp = service.register(email,name, pw, type);
        if(resp.contains("ERROR:")){
            model.addAttribute("error",true);
            model.addAttribute("msg", resp);
            return "register";
        }
        else{
            model.addAttribute("reg_success", true);
            return "login";
        }
    }
}
