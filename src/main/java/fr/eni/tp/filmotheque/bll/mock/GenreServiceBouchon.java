package fr.eni.tp.filmotheque.bll.mock;

import fr.eni.tp.filmotheque.bll.IGenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class GenreServiceBouchon implements IGenreService {
    private static List<Genre> listGenres = new ArrayList<>();
    private static int indexGenre = 1;
    public GenreServiceBouchon() {
        creerGenre(new Genre("Animation"));
        creerGenre(new Genre("Science-fiction"));
        creerGenre(new Genre("Documentaire"));
        creerGenre(new Genre("Action"));
    }

    @Override
    public List<Genre> consulterGenres() {
        return listGenres;
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return listGenres.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

  /*  @Override
    public Genre consulterGenreParId(long id) {
        // on recherche dans la liste le genre d'id "id"
        for (Genre genre : listeGenres) {
            if (genre.getId() == id){
                return genre;
            }
        }
        // on retourne null si pas trouvé
        return null;
    }*/

    @Override
    public void supprimerGenreParId(long id) {
        for(int index = 0; index < listGenres.size(); index++) {
            if(listGenres.get(index).getId() == id) {
                listGenres.remove(index);
            }
        }
    }

    @Override
    public void creerGenre(Genre genre) {

        genre.setId(indexGenre++);
        listGenres.add(genre);
    }


}
