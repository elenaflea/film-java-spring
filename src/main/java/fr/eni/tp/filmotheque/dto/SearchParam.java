package fr.eni.tp.filmotheque.dto;

import lombok.Data;

@Data
public class SearchParam {
    private String search;
    private String genre;
    private Integer dureeMin;
    private Integer dureeMax;
    private Integer anneeMin;
    private Integer anneeMax;
}

