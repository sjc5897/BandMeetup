package com.bandmeetup.controller;

import com.bandmeetup.services.SearchService;
import com.bandmeetup.model.Musician;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;



    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage(Model model) {
        List<Musician> musicians = searchService.displayallprofiles();
        for(Musician musi: musicians){
            model.addAttribute("FName", musi.getName());
            model.addAttribute("Status", musi.getStatus());
            model.addAttribute("Instruments", musi.getInstruments());
            model.addAttribute("Genre", musi.getGenre());
            model.addAttribute("Location", musi.getLocation());
        }
        
        return "search";
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String requestMethodName(@RequestParam(name = "searchBar") String searchentry) {
        
        return "";
    }
    
}
