package fr.eni.tp.filmotheque.bll.api;

import fr.eni.tp.filmotheque.bll.IFilmService;
import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dto.SearchParam;
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
public class FilmRestController {

    @Autowired
    private IFilmService filmService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private IParticipantService participantService;

    @Autowired
    private FilmRepository filmRepository;

    // dans tous les templates : "listeGenres" va être accessible et prendre la valeur de l'appel : genreService.consulterGenres();
    @ModelAttribute("listeGenres")
    public List<Genre> listeGenres(){
        return genreService.consulterGenres();
    }

    // dans tous les templates : "listeParticipants" va être accessible et prendre la valeur de l'appel : participantService.consulterParticipants();
    @ModelAttribute("listeParticipants")
    public  List<Participant> listeParticipants(){
        return participantService.consulterParticipants();
    }


    @GetMapping
    public List<Film> getFilms(SearchParam searchParam, Model model){

        if (searchParam.getSearch() != null){

            return filmService.rechercher(searchParam);
        }

        else{
            return filmService.consulterFilms();
        }

    }
    @GetMapping("/{idfilm}")
    public Film getFilm(@PathVariable("idFilm") long id){

        return filmService.consulterFilmParId(id);
    }

    @PostMapping
    public void postFilmCreation(@RequestBody @Valid Film film){

        filmService.creerFilm(film);
    }


    @PutMapping("/{id}")
    public void putFilm(@PathVariable("id") long id, @RequestBody @Valid Film film){
        film.setId(id);
        filmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable("id") long id){
        filmRepository.deleteById(id);
    }


    @PostMapping("/{idFilm}/avis")
    public void postFilmAvisForm(@RequestBody @Valid Avis avis,
                                 BindingResult bindingResult,
                                 @PathVariable("idFilm") long idFilm,
                                 @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte){
        filmService.publierAvis(avis, idFilm);
    }



}
