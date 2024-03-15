package fr.eni.tp.filmotheque.converter;

import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component // attention à bien mettre le converter dans le contexte Spring
public class GenreConverter implements Converter<String, Genre> {
    @Override
    public Genre convert(String idAuFormatTexte) {

        // TODO : retourner une instance de participant à partir d'un id au format texte
        return new Genre("Genre Bidon Generé Par le COnverter");
    }
}
