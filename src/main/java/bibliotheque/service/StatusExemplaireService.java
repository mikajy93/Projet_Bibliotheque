package bibliotheque.service;

import bibliotheque.entity.StatusExemplaire;
import bibliotheque.repository.StatusExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusExemplaireService {

    @Autowired
    private StatusExemplaireRepository statusExemplaireRepository;

    public List<StatusExemplaire> findAll() {
        return statusExemplaireRepository.findAll();
    }

    public Optional<StatusExemplaire> findById(int id) {
        return statusExemplaireRepository.findById(id);
    }

    public StatusExemplaire save(StatusExemplaire statusExemplaire) {
        return statusExemplaireRepository.save(statusExemplaire);
    }

    public void deleteById(int id) {
        statusExemplaireRepository.deleteById(id);
    }

    
}