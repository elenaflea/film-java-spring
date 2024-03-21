package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

/**
 * Interface
 * Ca n'est pas instancié
 * Donc, aucun interêt de mettre @Service dedans
 */
public interface IMembreService {

    List<Membre> consulterMembres();

    Membre consulterMembreParId(long id);

    Membre consulterMembreParPseudo(String pseudo);

    void supprimerMembreParId(long id);

    void creerMembre(Membre genre) throws Exception;
}
