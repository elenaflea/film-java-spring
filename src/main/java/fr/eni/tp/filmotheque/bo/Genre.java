package fr.eni.tp.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    public long id;
    @NotBlank
    public String titre;

    public Genre(String titre) {
        this.titre = titre;
    }
}
