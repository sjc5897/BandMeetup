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



    @RequestMapping(value = {"/search*"}, method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value = {"/search*"}, method=RequestMethod.POST)
    public String requestMethodName(@RequestParam(name = "searchBar") String searchentry, @RequestParam(name = "searchfilter") String filter, Model model) {
        List<Musician> musicians = searchService.search(searchentry, filter);
        model.addAttribute("musicians", musicians);

        return "search";
    }
    
}
