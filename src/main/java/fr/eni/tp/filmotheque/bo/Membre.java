package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Lombok n'arrive pas bien à gérer l'héritage
 * => on va générer les constructeurs à la main
 */
public class Membre extends Personne{

    private String pseudo;
    private String motDePasse;
    private boolean admin;

    /*
    * pas besoin d'avoir la liste des avis dans Membre
    * car on va toujurs accéder d'abord aux avis et ensuite au membre
    * => association unidirectionnelle portée par la classe Avis
    * List<Avis> avis = new ArrayList<>();
     */


    /*
    * Constructeurs
     */

    public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }
}
