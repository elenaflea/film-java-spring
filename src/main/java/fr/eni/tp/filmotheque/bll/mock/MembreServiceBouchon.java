package fr.eni.tp.filmotheque.bll.mock;

import fr.eni.tp.filmotheque.bll.IMembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class MembreServiceBouchon implements IMembreService {

    private  List<Membre> listeMembres = new ArrayList<>();
    private int idCourant = 1;
    public MembreServiceBouchon() {
        creerMembre(new Membre(1, "Scott", "Mickael", "mike", "123456"));
        creerMembre(new Membre(2, "Bizley", "Pamela", "pam", "123456"));
        creerMembre(new Membre(3, "Halper", "John", "john", "123456"));
        creerMembre(new Membre(3, "Schrute", "Dwight", "dwight", "123456"));
    }
    @Override
    public List<Membre> consulterMembres() {
        return listeMembres;
    }

    @Override
    public Membre consulterMembreParId(long id) {
        for (Membre membre : listeMembres) {
            if (membre.getId() == id){
                return membre;
            }
        }
        return null;
    }

    @Override
    public void supprimerMembreParId(long id) {
        for (int index = 0; index < listeMembres.size(); index++) {
            if (listeMembres.get(index).getId() == id){
                listeMembres.remove(index);
            }
        }

    }

    @Override
    public void creerMembre(Membre membre) {
        membre.setId(idCourant++);
        listeMembres.add(membre);
    }
}
