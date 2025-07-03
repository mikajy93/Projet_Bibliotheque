package bibliotheque.service;

import bibliotheque.entity.JourOuvrable;
import bibliotheque.repository.JourOuvrableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourOuvrableService {

    @Autowired
    private JourOuvrableRepository jourOuvrableRepository;

    public List<JourOuvrable> findAll() {
        return jourOuvrableRepository.findAll();
    }

    public Optional<JourOuvrable> findById(int id) {
        return jourOuvrableRepository.findById(id);
    }

    public JourOuvrable save(JourOuvrable jourOuvrable) {
        return jourOuvrableRepository.save(jourOuvrable);
    }

    public void deleteById(int id) {
        jourOuvrableRepository.deleteById(id);
    }
}