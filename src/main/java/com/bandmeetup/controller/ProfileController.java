package com.bandmeetup.controller;

import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;
import com.bandmeetup.model.VenueManager;
import com.bandmeetup.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class ProfileController extends HttpServlet {
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
    @RequestMapping(value="/edit/{email:.+}", method = GET)
    public String getEditPage(@PathVariable("email")String email, Model model, HttpServletRequest request){

        try {
            HttpSession session1 = request.getSession(false);
            String uname = (String) session1.getAttribute("email");
            String role = (String) session1.getAttribute("role");

            if (!uname.equals(email) && !role.equals("Admin")) {
                return "redirect:/cerror/access_denied";
            }
        }
        catch (Exception ex){
            return "redirect:/login";
        }

        System.out.println("Valid Request");

        addProfile(email,model);

        if(model.getAttribute("Profile") == null){
            return "redirect:/cerror/profile_not_found";
        }

        return "edit";
    }
    @RequestMapping(value="/edit/{email:.+}",method=RequestMethod.POST)
    public String submitEdit(@PathVariable("email") String email, @RequestParam("full_name") String name,
                             @RequestParam(value = "zipcode",required = false) Integer zip,
                             @RequestParam(value = "genre", required = false) String genre,
                             @RequestParam(value = "inst", required = false) String inst,
                             @RequestParam(value = "bio", required = false) String bio,
                             @RequestParam(value = "status", required = false) String status,
                             @RequestParam(value = "desc", required = false) String desc){

        User user = service.getUser(email);
        boolean resp = false;
        if (user.getUserType().equals("Musician")) {
            Musician profile = service.getMusician(email);
            profile.setBio(bio);
            profile.setGenre(genre);
            profile.setLocation(zip.toString());
            profile.setInstruments(inst);
            profile.setStatus(status);
            profile.setName(name);
            resp = service.updateMusician(profile);
        }
        else if(user.getUserType().equals("VenueManager")){
            VenueManager profile = service.getVenueManager(email);
            profile.setDescription(desc);
            profile.setName(name);
            profile.setLocation(zip.toString());
            resp = service.updateVenueManager(profile);
        }
        if(resp){
            return "redirect:/profile/" + email;
        }
        else{
            return "redirect:/edit/" + email;
        }

    }

    /**
     * This handles the get for the profile page
     * @param email the Id
     * @param model a fake model
     * @return
     */
    @RequestMapping(value="/profile/{email:.+}", method = RequestMethod.GET)
    public String displayLoginPage(@PathVariable("email")String email,Model model, HttpServletRequest request) {


        try {
            HttpSession session1 = request.getSession(false);
        }
        catch (Exception ex){  return "redirect:/login";}


        addProfile(email, model);

        if (model.getAttribute("Profile") == null) {
            return "redirect:/cerror/profile_not_found";
        }
        return "profile";
    }
}
