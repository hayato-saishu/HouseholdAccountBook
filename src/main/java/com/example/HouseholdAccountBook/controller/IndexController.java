package com.example.HouseholdAccountBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * Redirects the root URL ("/") to the index.html page.
     *
     * @return a String representing the forward directive to load the index.html page
     */
    @GetMapping("/")
    public String forward() {
        return "forward:/index.html";
    }
}
