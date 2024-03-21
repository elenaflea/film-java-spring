package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IFilmService;
import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import fr.eni.tp.filmotheque.dto.SearchParamFilm;
import fr.eni.tp.filmotheque.dto.SearchParamMembre;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private IFilmService filmService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private IParticipantService participantService;
    @Autowired
    private FilmRepository filmRepository;

    ;
    @ModelAttribute("listeGenres")
    public  List<Genre> listeGenres(){
        return genreService.consulterGenres();
    }

    @ModelAttribute("listeParticipants")
    public  List<Participant> listeParticipants(){
        return participantService.consulterParticipants();
    }


    @GetMapping
    public String getFilms(SearchParamFilm searchParam, Model model){


        if (searchParam.getSearch() != null ){

            Pageable pageable = PageRequest.of(searchParam.getCurrentPage(), 10);
            model.addAttribute("listeMembres", filmRepository.rechercher());
            model.addAttribute("searchParam", new SearchParamMembre());
        }
        else {
            model.addAttribute("listeFilms", filmService.consulterFilms());
        }

        return "films";
    }


    @GetMapping("/creer")
    public String getFilmCreation(Model model){

        model.addAttribute("film", new Film());

        return "filmCreation";
    }

    @PostMapping("/creer")
    public String postFilmCreation(@Valid Film film, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "filmCreation";
        }
        filmService.creerFilm(film);
        return "redirect:/films";
    }


    /**
     * est appelé lorsque j'accède à l'url : http://localhost:8080/films/{idFilm}
     * va mettre dans le model le film qui correspond à l'id passé en paramètre
     * afin de pouvoir l'afficher dans le HTML de mon template
     *
     * Pour recupérer l'id depuis l'url
     * on utilise un argument @PathVariable("idFilm") long idFilm
     */
    @GetMapping("/{idFilm}")
    public String getFilmDetail(@PathVariable("idFilm") long idFilm, Model model){

        // je mets dans le modèle un attribut "film" qui va contenir le film correspondant à l'id : idFilm
        model.addAttribute("film", filmService.consulterFilmParId(idFilm));
        /*
            je retourne le template filmDetail.html
            je vais pouvoir accéder à mon attribut film dans le template
            via la syntaxe ${film}
         */
        return "filmDetail";
    }

    /**
     * est appelé lorsque j'accède à l'url : http://localhost:8080/films/{idFilm}/avis
     * va mettre dans le model le film qui correspond à l'id passé en paramètre
     * afin de pouvoir l'afficher dans le HTML de mon template
     *
     * Pour recupérer l'id depuis l'url
     * on utilise un argument @PathVariable("idFilm") long idFilm
     */
    @GetMapping("/{idFilm}/avis")
    public String getFilmAvisForm(@PathVariable("idFilm") long idFilm, Model model){

        // je mets dans le modèle un attribut "film" qui va contenir le film correspondant à l'id : idFilm
        model.addAttribute("film", filmService.consulterFilmParId(idFilm));
        model.addAttribute("avis", new Avis());
        /*
            je retourne le template filmDetail.html
            je vais pouvoir accéder à mon attribut film dans le template
            via la syntaxe ${film}
         */
        return "avis";
    }

    /**
     * est appelé lorsque je valide le formulaire de création d'avis
     *
     * On va récupérer :
     * - l'avis rempli par le formulaire
     * - l'id du film correspondant à l'url
     * - l'utilisateur connecté avec @Aute
     *
     * va mettre dans le model le film qui correspond à l'id passé en paramètre
     * afin de pouvoir l'afficher dans le HTML de mon template
     *
     * Pour recupérer l'id depuis l'url
     * on utilise un argument @PathVariable("idFilm") long idFilm
     */
    @PostMapping("/{idFilm}/avis")
    public String postFilmAvisForm(@Valid Avis avis, BindingResult bindingResult, @PathVariable("idFilm") long idFilm, @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte){

        // si l'avis n'est pas valide
        if (bindingResult.hasErrors()){
            // je renvoie vers le template de création d'avis
            return "avis";
        }

        /**  sinon **/

        // 1- j'ajoute le membre connecté à l'avis
        avis.setMembre(utilisateurConnecte.getMembre());

        // 2 - je crée l'avis
        filmService.publierAvis(avis, idFilm);

        // 3 - je redirige ensuite sur la page de détail du film
        return "redirect:/films/" + idFilm;
    }


}
