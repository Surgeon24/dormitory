package krakow.university.dormitory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilitiesController {
    @GetMapping("/facilities")
    public String facilities() {
        return "facilities";
    }
}