package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participants")
public class ParticipantsController {

    @Autowired
    private IParticipantService participantService;


    @GetMapping
    public String getParticipants(
            Model model,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte
    ){
        model.addAttribute("participant", new Participant());
        model.addAttribute("listeParticipants", participantService.consulterParticipants());
        return "participants";
    }

    @PostMapping
    public String postParticipants(
            @Valid Participant participant,
            BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("listeParticipants", participantService.consulterParticipants());
            return "participants";
        }
        participantService.creerParticipant(participant);
        return "redirect:/participants";
    }

    @PostMapping("/{id}/supprimer")
    public String deleteParticipant(
            @PathVariable("id") long id,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte
    ){
        participantService.supprimerParticipantParId(id);
        return "redirect:/participants";
    }


}

