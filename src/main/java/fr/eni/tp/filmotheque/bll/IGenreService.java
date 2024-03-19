package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

/**
 * Interface
 * Ca n'est pas instancié
 * Donc, aucun interêt de mettre @Service dedans
 */
public interface IGenreService {

    List<Genre> consulterGenres();

    Genre consulterGenreParId(long id);

    void supprimerGenreParId(long id);

    void creerGenre(Genre genre);
}
