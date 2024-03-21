package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IMembreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import fr.eni.tp.filmotheque.dto.SearchParamMembre;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membres")
public class MembresController {

    @Autowired
    private IMembreService membreService;
    @Autowired
    private MembreRepository membreRepository;

    @GetMapping
    public String getMembres(
            SearchParamMembre searchParam,
            Model model,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte
    ){
        model.addAttribute("membre", new Membre());

        if (searchParam.getSearch() != null || searchParam.getPseudo() != null){

            Pageable pageable = PageRequest.of(searchParam.getCurrentPage(), 10);
            model.addAttribute("listeMembres", membreRepository.rechercher());
            model.addAttribute("searchParam", new SearchParamMembre());
        }
        else {
            model.addAttribute("listeMembres", membreService.consulterMembres());;
        }
        return "membres";



    }

    @PostMapping
    public String postMembres(@Valid Membre membre, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("listeMembres", membreService.consulterMembres());
            return "membres";
        }
        try{
            membreService.creerMembre(membre);
        }
        catch(Exception e){
            model.addAttribute("exception", e.getMessage());
            model.addAttribute("listeMembres", membreService.consulterMembres());
            return "membres";
        }
        return "redirect:/membres";
    }

    @PostMapping("/{id}/supprimer")
    public String deleteMembre(
            @PathVariable("id") long id,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte){
        membreService.supprimerMembreParId(id);
        return "redirect:/membres";
    }


}

