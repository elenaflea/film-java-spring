package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface IMembreService {

    List<Membre> consulterMembres();

    Membre consulterMembreParId(long id);

    void supprimerMembreParId(long id);

    void creerMembre(Membre genre) throws Exception;

    Membre consulterMembreParPseudo(String username);
}
