package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.IMembreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import fr.eni.tp.filmotheque.dto.SearchParamMembre;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/membres")
public class MembreRestController {
    @Autowired
    private IMembreService membreService;
    @Autowired
    private MembreRepository membreRepository;

    @GetMapping
    public List getMembres(
    ){
        return membreService.consulterMembres();
    }

    @GetMapping("/{id}")
    public Membre getMembre(@PathVariable("id") long id){
        return membreRepository.findById(id).get();
    }

    @PostMapping
    public void postMembre(@RequestBody @Valid Membre membre){
try{
    membreService.creerMembre(membre);
}
catch (Exception e) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
}

    }


    @PutMapping("/{id}")
    public void putMembre(@PathVariable("id") long id,
                          @RequestBody @Valid Membre membre){
        membre.setId(id);
        membreRepository.save(membre);
    }

    @DeleteMapping("/{id}")
    public void deleteMembre(@PathVariable("id") long id){

        membreService.supprimerMembreParId(id);
    }


}
