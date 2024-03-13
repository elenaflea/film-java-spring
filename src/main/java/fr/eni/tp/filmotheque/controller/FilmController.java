package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    // on crée un attribut de type IFilmService qui va être auto-injecté par Spring
    /* si on veut injecter un service, il faut penser à mettre une instance de classe dans le coontexte Spring
    * (en ajoutant @Service avant le nom de la classe) */
  //  @Autowired
   // private IFilmService filmService;

    /**
     * Dans cette méthode, je vais appeler consulterFilmParId() de FilmServiceBouchon
     * via l'interface IFilmService
     */
  //  public void afficherUnFilm(int i) {
   //     System.out.println(filmService.consulterFilmParId(i));
  //  }

    /**
     * Dans cette méthode, je vais appeler consulterFilms() de FilmServiceBouchon
     * via l'interface IFilmService
     */
  //  public void afficherFilms() {
  //      for (Film film : filmService.consulterFilms()) {
     //       System.out.println(film);
     //   }
  //  }

    private int idCourant = 1;
    List<Film> listeFilms = new ArrayList<>();
    @GetMapping
    public String getFilms(Model model){

        // on ajoute dans le modèle la liste des formateurs à afficher
        model.addAttribute("listeFilms", listeFilms);
        return "films";
    }


    @GetMapping("/{id}")
    public String getFilmDetail(@PathVariable("id") long idFilm, Model model){
        for (Film film : listeFilms) {
            if (film.getId() == idFilm){
                model.addAttribute("film", film);
            }
        }
        return "filmDetail";
    }


    @PostMapping
    public String postFilm(
            @RequestParam String titre,
            @RequestParam int annee,
            @RequestParam int duree,
            @RequestParam String synopsis
    ){

        Film film = new Film(idCourant, titre, annee, duree, synopsis);
        idCourant++;
        listeFilms.add(film);
        return "redirect:/films";
    }
}
