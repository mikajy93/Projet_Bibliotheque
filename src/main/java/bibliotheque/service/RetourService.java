package bibliotheque.service;

import bibliotheque.entity.Retour;
import bibliotheque.repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetourService {

    @Autowired
    private RetourRepository retourRepository;

    public List<Retour> findAll() {
        return retourRepository.findAll();
    }

    public Optional<Retour> findById(int id) {
        return retourRepository.findById(id);
    }

    public Retour save(Retour retour) {
        return retourRepository.save(retour);
    }

    public void deleteById(int id) {
        retourRepository.deleteById(id);
    }
}