package fr.eni.tp.filmotheque.bll.api;

import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/participants")
public class ParticipantsRestController {

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private ParticipantRepository participantRepository;


    @GetMapping
    public List getParticipants(
            Model model,
            @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte
    ){
        return participantService.consulterParticipants();
    }

    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable("id") long id){
        return participantService.consulterParticipantParId(id);
    }


    @PostMapping
    public void postParticipant(@RequestBody @Valid Participant participant){

        participantService.creerParticipant(participant);
    }



    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") long id){

        participantService.supprimerParticipantParId(id);
    }

}
