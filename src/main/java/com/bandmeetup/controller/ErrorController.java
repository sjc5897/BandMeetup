package com.bandmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * This is the Custom Error, handles GETs and POSTs to profile
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 11/3/20
 * Last Edit: 11/3/20
 */
@Controller
public class ErrorController {

    @RequestMapping(value="/cerror/{error}", method = RequestMethod.GET)
    public String displayErrorPage(@PathVariable("error")String error, Model model){
        model.addAttribute("error_type", error);
        return "cerror";
    }
}
