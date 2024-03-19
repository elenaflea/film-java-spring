package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.IParticipantService;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface IParticipantService
 * qui va être injectée dans le controller ParticipantController
 *
 * @Service permet de rendre une instance de cette classe
 * disponible dans le contexte Spring (obligatoire afin d'être injectée)
 *
 * @Profile("prod")
 * Specifie que cette classe est activée UNIQUEMENT lorsqu'on a le profil "prod"
 * dans application.properties
 */
@Service
@Profile("prod")
/**
 * TODO A Modifier plus tard pour utiliser le repository
 */
public class ParticipantServiceJpaImpl implements IParticipantService {

    /*
     * Dans l'implémentation "bouchon"
     * on gère les participants dans une liste
     */
    private  List<Participant> listeParticipants = new ArrayList<>();

    /*
     * On initialise la liste des participants
     */
    public ParticipantServiceJpaImpl() {
        creerParticipant(new Participant("Spielberg", "Steven"));
        creerParticipant(new Participant("Cronenberg", "David"));
        creerParticipant(new Participant("Boon", "Dany"));
        creerParticipant(new Participant("Attenborough", "Richard"));
        creerParticipant(new Participant("Davis", "Geena"));
        creerParticipant(new Participant("Rylance", "Mark"));
        creerParticipant(new Participant("Merad", "Kad"));

    }



    // on gère un compteur pour l'id (qui simule l'auto-increment de la base de donnée)
    private int idCourant = 1;

    @Override
    public List<Participant> consulterParticipants() {
        return listeParticipants;
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        // on recherche dans la liste le participant d'id "id"
        for (Participant participant : listeParticipants) {
            if (participant.getId() == id){
                return participant;
            }
        }
        // on retourne null si pas trouvé
        return null;
    }

    @Override
    public void supprimerParticipantParId(long id) {
        // on recherche dans la liste l'index correspondant au participant d'id "id"
        for (int index = 0; index < listeParticipants.size(); index++) {
            if (listeParticipants.get(index).getId() == id){
                listeParticipants.remove(index);
            }
        }
    }

    @Override
    public void creerParticipant(Participant participant) {
        // on rajoute l'id au participant (tout en l'incrémentant)
        participant.setId(idCourant++);
        // on rajoute le participant à la liste
        listeParticipants.add(participant);
    }
}
