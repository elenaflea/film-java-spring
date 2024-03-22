package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.IFilmService;
import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.AvisRepository;
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
@RequestMapping("/api/films")
public class AvisRestController {

    @Autowired
    private IFilmService filmService;


    @Autowired
    private AvisRepository avisRepository;


   /* @GetMapping("/{idFilm}/avis")
    public List getAllAvisParFilm(@PathVariable("idFilm") long idFilm, Model model){
        return avisRepository.findAll();

    }
*/
 /*   @GetMapping("/{idFilm}/avis/{id}")
    public Avis getAvis(@PathVariable("idFilm") long idFilm, @PathVariable("id") long id){
        return avisRepository.findById(id).get();
    }
*/
/*
    @PostMapping("/{idFilm}/avis")
    public void postFilmAvisForm(@Valid Avis avis, BindingResult bindingResult, @PathVariable("idFilm") long idFilm, @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte){
        avisRepository.save(avis);
    }*/

    @PutMapping("/{idFilm}/avis/{id}")
    public void putAvis(@PathVariable("idFilm") long idFilm, @PathVariable("id") long id, @RequestBody @Valid Avis avis){
        avis.setId(id);
        avisRepository.save(avis);
    }

    @DeleteMapping("/{idFilm}/avis/{id}")
    public void deleteAvis(@PathVariable("id") long id, @PathVariable("idFilm") long idFilm){

        avisRepository.deleteById(id);
    }

}
