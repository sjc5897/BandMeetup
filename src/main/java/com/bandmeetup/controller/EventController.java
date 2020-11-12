package com.bandmeetup.controller;

import com.bandmeetup.model.Event;
import com.bandmeetup.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.channels.ScatteringByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EventController {
    @Autowired
    EventService service;

    @RequestMapping(value="new_event", method= RequestMethod.GET)
    public String getEventCreatePage(HttpServletRequest request){
        try {
            HttpSession session1 = request.getSession(false);
            String uname = (String) session1.getAttribute("email");
            String role = (String) session1.getAttribute("role");
            if (!role.equals("VenueManager")) {
                return "redirect:/cerror/access_denied";
            }
        }
        catch (Exception ex){
            return "redirect:/login";
        }
        return "new_event";

    }
    @RequestMapping(value="/new_event", method= RequestMethod.POST)
    public String createEvent(@RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("date") String Date
                              ,HttpServletRequest request, Model model){
        try {
            HttpSession session1 = request.getSession(false);
            String uname = (String) session1.getAttribute("email");
            String role = (String) session1.getAttribute("role");
            if (!role.equals("VenueManager")) {
                return "redirect:/cerror/access_denied";
            }
            if(service.createEvent(title,desc,Date,uname)){
                return "redirect:/profile/" + uname;
            }
            else{
                model.addAttribute("error",true);
                return "new_event";
            }

        }
        catch (Exception ex){
            return "redirect:/login";
        }

    }
    @RequestMapping(value="/edit_event/{id}", method = RequestMethod.GET)
    public String editEventPage(@PathVariable("id")Integer id, Model model, HttpServletRequest request){
        String uname;
        String role;
        try {
            HttpSession session1 = request.getSession(false);

            uname = (String) session1.getAttribute("email");
            role = (String) session1.getAttribute("role");

            if (!role.equals("VenueManager")) {
                return "redirect:/cerror/access_denied";
            }
        }
        catch (Exception ex){
            return "redirect:/login";
        }

        Event e = service.getEventById(id);

        if(!uname.equals(e.getVenueManager().getEmail())){
            return "redirect:/cerror/access_denied";
        }
        model.addAttribute("event",e);
        return "edit_event";

    }
    @RequestMapping(value="/edit_event/{id}", method = RequestMethod.POST)
    public String sumbitEventEdit(@PathVariable("id")Integer id, Model model, HttpServletRequest request,
                                  @RequestParam("title") String title, @RequestParam("desc") String desc,
                                  @RequestParam("date") String Date){

        HttpSession session1 = request.getSession(false);

        String uname = (String) session1.getAttribute("email");
        String role = (String) session1.getAttribute("role");

        if (!role.equals("VenueManager")) {
            return "redirect:/cerror/access_denied";
        }

        Event e = service.getEventById(id);

        if(!uname.equals(e.getVenueManager().getEmail())){
            return "redirect:/cerror/access_denied";
        }
        try {
            e.setTitle(title);
            e.setDescription(desc);
            System.out.println(Date);
            e.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(Date));
            System.out.println(e.getDate());
            service.update(e);
        }
        catch (Exception ex){
            model.addAttribute("error",true);
            return "redirect:/edit_event/"+id;
        }
        return "redirect:/profile/" + uname;
    }

    @RequestMapping(value="/delete_event/{id}", method = RequestMethod.POST)
    public String deleteEvent(@PathVariable("id")Integer id, Model model, HttpServletRequest request){
        String uname;
        String role;
        try {
            HttpSession session1 = request.getSession(false);

            uname = (String) session1.getAttribute("email");
            role = (String) session1.getAttribute("role");

            if (!role.equals("VenueManager")) {
                return "redirect:/cerror/access_denied";
            }
        }
        catch (Exception ex){
            return "redirect:/login";
        }

        Event e = service.getEventById(id);

        if(!uname.equals(e.getVenueManager().getEmail())){
            return "redirect:/cerror/access_denied";
        }

        service.deleteEvent(e);
        return "redirect:/profile/" + uname;

    }

}
