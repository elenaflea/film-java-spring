package fr.eni.tp.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avis {
    public long id;
    public int note;
    public String commentaire;

    /*
    * ASSOCIATIONS Membre <-> Avis
     */
    private Membre membre;

    /*
     * pas besoin d'avoir le film associé à l'avis
     * car on va toujurs accéder d'abord aux film et ensuite aux avis associés
     * => association unidirectionnelle portée par la classe Film
     * Film film;
     */
}
