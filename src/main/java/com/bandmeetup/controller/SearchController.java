package com.bandmeetup.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String requestMethodName(@RequestParam(name = "searchBar") String searchentry) {
        
        return "";
    }
    
}
