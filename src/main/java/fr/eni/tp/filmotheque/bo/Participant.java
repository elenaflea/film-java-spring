package fr.eni.tp.filmotheque.bo;

/**
 * Lombok n'arrive pas bien à gérer l'héritage
 * => on va générer les constructeurs à la main
 */
public class Participant extends Personne{



    /*
    * Constructeurs
     */
    public Participant(long id, String nom, String prenom) {
        super(id, nom, prenom);
    }


}
