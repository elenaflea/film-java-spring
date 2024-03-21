package fr.eni.tp.filmotheque.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class SearchParamFilm {

    private String search;
    private String titre;
    private String nom;
    private String prenom;
    private String acteurs;
    private String realisateur;
    private int currentPage = 0;
}
