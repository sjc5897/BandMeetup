package com.bandmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class searchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaysearchPage() {
        return "search";
    }

    @RequestMapping(value = "/searchDisplay", method = RequestMethod.GET)
    public String displaysearchDisplayPage() {
        return "searchDisplay";
    }
}
