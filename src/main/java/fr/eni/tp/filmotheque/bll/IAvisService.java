package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface IAvisService {

    List<Avis> consulterAvis();

    Avis consulterAvisParId(long id);

    void supprimerAvisParId(long id);

    void creerAvis(Avis avis);
}
