package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
}
