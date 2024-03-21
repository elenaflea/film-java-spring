package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

/**
 * Interface
 * Ca n'est pas instancié
 * Donc, aucun interêt de mettre @Service dedans
 */
public interface IParticipantService {

    List<Participant> consulterParticipants();

    Participant consulterParticipantParId(long id);

    void supprimerParticipantParId(long id);

    void creerParticipant(Participant genre);
}
