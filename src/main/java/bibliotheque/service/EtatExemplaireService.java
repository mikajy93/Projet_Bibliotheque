package bibliotheque.service;

import bibliotheque.entity.EtatExemplaire;
import bibliotheque.repository.EtatExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatExemplaireService {

    @Autowired
    private EtatExemplaireRepository etatExemplaireRepository;

    public List<EtatExemplaire> findAll() {
        return etatExemplaireRepository.findAll();
    }

    public Optional<EtatExemplaire> findById(int id) {
        return etatExemplaireRepository.findById(id);
    }

    public EtatExemplaire save(EtatExemplaire etatExemplaire) {
        return etatExemplaireRepository.save(etatExemplaire);
    }

    public void deleteById(int id) {
        etatExemplaireRepository.deleteById(id);
    }
}