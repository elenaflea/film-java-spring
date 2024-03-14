package fr.eni.tp.filmotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membres")
public class MembresController {


    @GetMapping
    public String getMembres(Model model){
        // A compléter
        return "membres";
    }

    // A Faire également : @PostMapping


}

