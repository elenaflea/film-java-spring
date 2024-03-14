package fr.eni.tp.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Membre extends Personne{
    @NotBlank
    private String pseudo;
    @NotBlank
    private String motDePasse;
    private boolean admin;
    public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public Membre() {
        super();
    }
}
