package bibliotheque.service;

import bibliotheque.entity.Adherent;
import bibliotheque.entity.Pret;
import bibliotheque.entity.Retour;
import bibliotheque.repository.AdherentRepository;
import bibliotheque.repository.PretRepository;
import bibliotheque.repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    /**
     * Valide le retour d'un prêt et incrémente le quota individuel de l'adhérent.
     */
    public void retournerPret(int idPret) {
        Optional<Pret> pretOpt = pretRepository.findById(idPret);
        if (!pretOpt.isPresent()) {
            throw new RuntimeException("Prêt non trouvé.");
        }
        Pret pret = pretOpt.get();
        if (pret.getDateRetourReelle() != null) {
            throw new RuntimeException("Ce prêt a déjà été retourné.");
        }
        pret.setDateRetourReelle(new Date());
        pretRepository.save(pret);

        // Incrémente le quota individuel
        Adherent adherent = pret.getAdherent();
        adherent.setQuotaRestant(adherent.getQuotaRestant() + 1);
        adherentRepository.save(adherent);
    }
}