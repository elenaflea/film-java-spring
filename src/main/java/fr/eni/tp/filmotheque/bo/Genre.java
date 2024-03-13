package fr.eni.tp.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    public long id;
    public String titre;


    /*
     * pas besoin d'avoir la liste des films dans Genre
     * car on va toujurs accéder d'abord aux film et ensuite au genre associé
     * => association unidirectionnelle portée par la classe Film
     * List<Film> films = new ArrayList<>();
     */
}
