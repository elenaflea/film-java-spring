package fr.eni.tp.filmotheque.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class SearchParamMembre {
    private String search;
    private String pseudo;
    private int currentPage = 0;
}
