package com.example.HouseholdAccountBook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String forward() {
        return "forward:/index.html";
    }
}
