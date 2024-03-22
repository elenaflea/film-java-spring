package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.IFilmService;
import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dto.SearchParam;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/films") // toutes les urls de mon controller vont être accessibles avec le prefixe : /films
public class FilmRestController {

    // on crée un attribut de type IFilmService qui va être auto-injecté par Spring
    /* si on veut injecter un service, il faut penser à mettre une instance de classe dans le coontexte Spring
     * (en ajoutant @Service avant le nom de la classe) */
    @Autowired
    private IFilmService filmService;


    @GetMapping
    public  List<Film>  getFilms(SearchParam searchParam){

        // Si jamais j'ai un paramètre de recherche
        if (searchParam.getSearch() != null){
            // je retourne les films possèdant le titre donné
            return filmService.rechercher(searchParam);
        }
        // sinon, je retourne tous les films
        else{
            return filmService.consulterFilms();
        }
    }


    @PostMapping
    public void postFilmCreation(@RequestBody @Valid Film film){
        // on ajoute le film
        filmService.creerFilm(film);
    }



    @GetMapping("/{idFilm}")
    public Film getFilmDetail(@PathVariable("idFilm") long idFilm){
        return filmService.consulterFilmParId(idFilm);
    }

    /**
     * TODO : actuellement, nous n'avons pas mis en place la securité pour les Web Services donc
     * @AuthenticationPrincipal ne va pas fonctionner
     */
    @PostMapping("/{idFilm}/avis")
    public void postFilmAvisForm(@RequestBody @Valid Avis avis, @PathVariable("idFilm") long idFilm, @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte){

        // TODO (quand on aura mis en place la securité) - j'ajoute le membre connecté à l'avis
        // avis.setMembre(utilisateurConnecte.getMembre());

        // 2 - je crée l'avis
        filmService.publierAvis(avis, idFilm);
    }
}
