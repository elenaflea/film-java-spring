package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;

public class Membre extends Personne{

    private String pseudo;
    private String motDePasse;
    private boolean admin;
    public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }
}
