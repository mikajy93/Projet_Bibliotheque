package bibliotheque.service;

import bibliotheque.entity.Auteur;
import bibliotheque.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    public List<Auteur> findAll() {
        return auteurRepository.findAll();
    }

    public Optional<Auteur> findById(int id) {
        return auteurRepository.findById(id);
    }

    public Auteur save(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public void deleteById(int id) {
        auteurRepository.deleteById(id);
    }
}