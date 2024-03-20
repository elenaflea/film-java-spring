package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.IMembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("prod")
public class MembreServiceJpaImpl implements IMembreService {

    // @Autowired : pas besoin car PasswordEncoder est injecté dans le constructeur
    private MembreRepository membreRepository;
    // @Autowired : pas besoin car PasswordEncoder est injecté dans le constructeur
    private PasswordEncoder passwordEncoder;

    public MembreServiceJpaImpl(PasswordEncoder passwordEncoder, MembreRepository membreRepository) throws Exception{
        this.passwordEncoder = passwordEncoder;
        this.membreRepository = membreRepository;


        if (membreRepository.findAll().size() == 0){
            creerMembre(new Membre("admin", "admin", "admin","admin", true));
        }

    }

    @Override
    public List<Membre> consulterMembres() {
        return membreRepository.findAll();
    }

    @Override
    public Membre consulterMembreParId(long id) {
        return membreRepository.findById(id).orElse(null); // si on utilise .orElse(null); à la place de .get(), ca ne plante pas si l'utilisateur n'existe pas mais ca renvoie null
    }

    @Override
    public Membre consulterMembreParPseudo(String pseudo) {
        for (Membre membre : consulterMembres()) {
            if (membre.getPseudo().equals(pseudo)) {
                return membre;
            }
        }
        return null;
    }

    @Override
    public void supprimerMembreParId(long id) {
        membreRepository.deleteById(id);
    }

    @Override
    public void creerMembre(Membre membre) throws Exception {

        // VALIDATION : je vais vérifier qu'un membre avec le même pseudo n'existe pas déjà en base
        // TODO : lancer un autre type d'exception plus specifique qui sera reprise par le template
        if (consulterMembreParPseudo(membre.getPseudo()) != null){
            throw new Exception("Erreur : Le pseudo est déjà utilisé par quelqu'un d'autre");
        }

        // NE PAS OUBLIER de mettre à jour le mot de passe en l'encodant
        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));

        // on sauvegarde le membre en base de donnée
        membreRepository.save(membre);
    }
}
