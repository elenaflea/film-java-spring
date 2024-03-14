package fr.eni.tp.filmotheque.bll;


import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface IParticipantService {

    List<Participant> consulterParticipants();

    Participant consulterParticipantParId(long id);

    void supprimerParticipantParId(long id);

    void creerParticipant(Participant participant);
}
