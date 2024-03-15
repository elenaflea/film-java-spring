package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenresController {


    @Autowired
    private IGenreService genreService;

    @GetMapping
    public String getGenres(Model model){
        model.addAttribute("genre", new Genre());
        model.addAttribute("listeGenres", genreService.consulterGenres());
        return "genres";

    }


    @PostMapping
    public String Genres(
            @Valid Genre genre,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()) {
            return "genres";
        }
        genreService.creerGenre(genre);
        return "redirect:/genres";
    }

    @PostMapping("/{id}/supprimer")
    public String deleteGenre(@PathVariable("id") long id){
        genreService.supprimerGenreParId(id);
        return "redirect:/genres";
    }



}
