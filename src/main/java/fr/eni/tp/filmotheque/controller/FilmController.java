package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IFilmService;
import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    // on crée un attribut de type IFilmService qui va être auto-injecté par Spring
    /* si on veut injecter un service, il faut penser à mettre une instance de classe dans le coontexte Spring
    * (en ajoutant @Service avant le nom de la classe) */
    @Autowired
    private IFilmService filmService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private IParticipantService participantService;

    private int idCourant = 1;
    List<Film> listeFilms = new ArrayList<>();

    @ModelAttribute("listeGenres")
    public  List<Genre> listeGenres(){
        return genreService.consulterGenres();
    }
    @ModelAttribute("listeParticipants")
    public  List<Participant> listeParticipants(){
        return participantService.consulterParticipants();
    }

    @GetMapping
    public String getFilms(Model model){
        model.addAttribute("listeFilms", filmService.consulterFilms());
        return "films";
    }


    @GetMapping("/{id}")
    public String getFilmDetail(@PathVariable("id") long idFilm, Model model){

        model.addAttribute("film", filmService.consulterFilmParId(idFilm));
        return "filmDetail";
    }


    @PostMapping
    public String postFilm(
            @Valid Film film,
            Model model
    ){
        model.addAttribute("film", new Film());
        model.addAttribute("listeGenres", genreService.consulterGenres());
        model.addAttribute("listeParticipants", participantService.consulterParticipants());

        idCourant++;
        listeFilms.add(film);
        return "redirect:/films";
    }


    @GetMapping("/creer")
    public String getForm(Model model){
        model.addAttribute("listeFilms", filmService.consulterFilms());
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
}
