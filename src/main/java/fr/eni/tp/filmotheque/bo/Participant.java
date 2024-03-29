package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;

@Entity
public class Participant extends Personne{

    public Participant(long id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    public Participant(String nom, String prenom) {
        super(nom, prenom);
    }
    public Participant() {
        super();
    }
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
