package com.bandmeetup.controller;

import com.bandmeetup.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.bandmeetup.security.Hasher.hashPass;

/**
 * This is the LoginController, handles GETs and POSTs to login
 * Note: For some reason IDE wouldn't let me name it LoginController
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/17/20
 * Last Edit: 10/19/20
 */

@Controller
public class LogController {
    @Autowired
    LoginService service; //The service that assists Login

    /**
     * The method that handles Login page requests
     * @return String Login, which is used to represent the login page
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String displayLoginPage(){
        return "login";
    }

    /**
     * The Method that handles POST requests of the login
     * @param name  The inputted name in the form
     * @param pw    The password provided
     * @param model TBH IDK, this was in the tutorial
     * @return      String representing redirect to greeting page OR back to login
     */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String runLogin(@RequestParam(name="uname") String name, @RequestParam(name="pw") String pw, Model model) {
        pw = hashPass(pw);
        if (service.validateUser(name,pw)){
            model.addAttribute("name", name);
            model.addAttribute("password", pw);
            // TODO: We need some robust role system
            return "home";
        }
        else{
            model.addAttribute("error", true);
            return "login";
        }
    }
}
