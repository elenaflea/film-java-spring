package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/genres")
public class GenreRestController {

    @Autowired
    private IGenreService genreService;

    @Autowired
    private GenreRepository genreRepository;


    @GetMapping
    public List<Genre> getGenres(){
        return genreService.consulterGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") long id){
        return genreRepository.findById(id).get();
    }

    @PostMapping
    public void postGenres(@RequestBody @Valid Genre genre){
        genreService.creerGenre(genre);
    }


    @PutMapping("/{id}")
    public void putGenre(@PathVariable("id") long id, @RequestBody @Valid Genre genre){
        genre.setId(id);
        genreRepository.save(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") long id){

        genreService.supprimerGenreParId(id);
    }

}
