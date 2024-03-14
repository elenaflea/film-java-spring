package fr.eni.tp.filmotheque.bll.mock;


import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class ParticipantServiceBouchon implements IParticipantService {
    private  List<Participant> listeParticipants = new ArrayList<>();
    public ParticipantServiceBouchon() {
        creerParticipant(new Participant("Spielberg", "Steven"));
        creerParticipant(new Participant("Cronenberg", "David"));
        creerParticipant(new Participant("Boon", "Dany"));
        creerParticipant(new Participant("Attenborough", "Richard"));
        creerParticipant(new Participant("Davis", "Geena"));
        creerParticipant(new Participant("Rylance", "Mark"));
    }

    private int idCourant = 1;

    @Override
    public List<Participant> consulterParticipants() {
        return listeParticipants;
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        for (Participant participant : listeParticipants) {
            if (participant.getId() == id){
                return participant;
            }
        }
        return null;
    }

    @Override
    public void supprimerParticipantParId(long id) {
        for (int index = 0; index < listeParticipants.size(); index++) {
            if (listeParticipants.get(index).getId() == id){
                listeParticipants.remove(index);
            }
        }
    }

    @Override
    public void creerParticipant(Participant participant) {
        participant.setId(idCourant++);
        listeParticipants.add(participant);
    }
}
