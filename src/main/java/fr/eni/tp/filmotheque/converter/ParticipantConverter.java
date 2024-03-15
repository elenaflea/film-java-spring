package fr.eni.tp.filmotheque.converter;

import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component // attention à bien mettre le converter dans le contexte Spring
public class ParticipantConverter implements Converter<String, Participant> {

    @Override
    public Participant convert(String idAuFormatTexte) {

        // TODO : retourner une instance de participant à partir d'un id au format texte
        return new Participant("Prenom Bidon Generé Par le COnverter", "Nom Bidon Generé Par le COnverter");
    }
}