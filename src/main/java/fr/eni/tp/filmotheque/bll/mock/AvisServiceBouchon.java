package fr.eni.tp.filmotheque.bll.mock;

import fr.eni.tp.filmotheque.bll.IAvisService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class AvisServiceBouchon implements IAvisService {

    private static List<Avis> listeAvis = new ArrayList<>();
    private static int indexAvis = 1;
    public AvisServiceBouchon() {
        creerAvis(new Avis(1, 2, "OK", new Membre()));

    }

    @Override
    public List<Avis> consulterAvis() {
        return listeAvis;
    }

    @Override
    public Avis consulterAvisParId(long id) {
        return listeAvis.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    @Override
    public void supprimerAvisParId(long id) {
        for(int index = 0; index < listeAvis.size(); index++) {
            if(listeAvis.get(index).getId() == id) {
                listeAvis.remove(index);
            }
        }
    }

    @Override
    public void creerAvis(Avis avis) {
        avis.setId(indexAvis++);
        listeAvis.add(avis);
    }
}
