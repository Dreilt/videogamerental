package pl.patryk.videogamerental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping(value = {"/", "/index"})
    public String showMainPage() {
        return "index";
    }
}
