package fr.eni.tp.filmotheque.bll.mock;

import fr.eni.tp.filmotheque.bll.IMembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class MembreServiceBouchon implements IMembreService {

    private  List<Membre> listeMembres = new ArrayList<>();
    @Autowired
    PasswordEncoder passwordEncoder;
    private int idCourant = 1;
    public MembreServiceBouchon(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        creerMembre(new Membre("Mace", "Cyril", "cyril","Pa$$w0rd", true));
        creerMembre(new Membre("Martin", "Aurélie", "membre", "Pa$$w0rd", false));
        creerMembre(new Membre("Scott", "Mickael", "mike", "123456", false));
        creerMembre(new Membre( "Bizley", "Pamela", "pam", "123456", true));
        creerMembre(new Membre("Halper", "John", "john", "123456", true));
        creerMembre(new Membre( "Schrute", "Dwight", "dwight", "123456", false));
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
    public Membre consulterMembreParPseudo(String pseudo) {
        for (Membre membre : listeMembres) {
            if (membre.getPseudo().equals(pseudo)) {
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
        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
        listeMembres.add(membre);
    }
}
