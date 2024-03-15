package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IAvisService;
import fr.eni.tp.filmotheque.bo.Avis;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private IAvisService avisService;
    @GetMapping
    public String getAvis(Model model){
        model.addAttribute("avis", new Avis());
        model.addAttribute("listeAvis", avisService.consulterAvis());
        return "avis";
    }

    @PostMapping
    public String Aviss(
            @Valid Avis avis,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()) {
            return "avis";
        }
        avisService.creerAvis(avis);
        return "redirect:/avis";
    }

    @PostMapping("/{id}/supprimer")
    public String deleteAvis(@PathVariable("id") long id){
        avisService.supprimerAvisParId(id);
        return "redirect:/avis";
    }
}
