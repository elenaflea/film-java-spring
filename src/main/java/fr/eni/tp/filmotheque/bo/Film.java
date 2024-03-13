package fr.eni.tp.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    public long id;
    public String titre;
    public int annee;
    public int duree;
    public String synopsis;

    /*
    * ASSOCIATIONS
     */
    private Genre genre;
    private List<Avis> avis = new ArrayList<>();
    private Participant realisateur;
    private List<Participant> acteurs = new ArrayList<>();

    /**
     * Constructeurs
     * Doivent être déclarées explicitement pour un nombre d'argument qui
     * n'est pas "Tous les arguments" iou "Aucun argument" (lombok ne sait pas faire)
     */
    public Film(long id, String titre, int annee, int duree, String synopsis) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }
}
