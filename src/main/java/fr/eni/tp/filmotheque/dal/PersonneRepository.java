package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne,Long> {
}
