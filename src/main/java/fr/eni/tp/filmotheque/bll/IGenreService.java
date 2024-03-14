package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface IGenreService {

    List<Genre> consulterGenres();

    Genre consulterGenreParId(long id);

    void supprimerGenreParId(long id);

    void creerGenre(Genre genre);


}
