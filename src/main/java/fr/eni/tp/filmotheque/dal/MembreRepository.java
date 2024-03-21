package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembreRepository extends JpaRepository<Membre,Long> {


    @Query("select f from Membre f where f.nom like :search% or f.prenom like:search% or f.pseudo like :search%")
    List<Membre> rechercher();
}
