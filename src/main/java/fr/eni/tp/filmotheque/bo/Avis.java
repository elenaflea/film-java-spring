package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Min(0) @Max(5) @NotNull
    public int note;
    public String commentaire;

    /*
     * ASSOCIATIONS Membre <-> Avis
     */
    //  Si on suit la cardinalité du diagramme de classe, on a Avis * -> 1 Membre
    // * -> 1   = @ManyToOne
    @ManyToOne
    private Membre membre;

    @ManyToOne
    Film film;


    public void setFilm(Film film) {
        this.film = film; // j'ajoute le film à avis
        film.getAvis().add(this); // j'ajoute par la même occasion l'avis au film
    }


}
