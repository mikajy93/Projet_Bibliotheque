package bibliotheque.service;

import bibliotheque.entity.JourFerie;
import bibliotheque.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    public List<JourFerie> findAll() {
        return jourFerieRepository.findAll();
    }

    public Optional<JourFerie> findById(int id) {
        return jourFerieRepository.findById(id);
    }

    public JourFerie save(JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }

    public void deleteById(int id) {
        jourFerieRepository.deleteById(id);
    }
}