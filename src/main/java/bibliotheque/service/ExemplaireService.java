package bibliotheque.service;

import bibliotheque.entity.Exemplaire;
import bibliotheque.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    public Optional<Exemplaire> findById(int id) {
        return exemplaireRepository.findById(id);
    }

    public Exemplaire save(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public void deleteById(int id) {
        exemplaireRepository.deleteById(id);
    }
}