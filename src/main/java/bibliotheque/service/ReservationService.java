package bibliotheque.service;

import bibliotheque.entity.Adherent;
import bibliotheque.entity.Exemplaire;
import bibliotheque.entity.Reservation;
import bibliotheque.entity.StatutReservation;
import bibliotheque.repository.AbonnementRepository;
import bibliotheque.repository.AdherentRepository;
import bibliotheque.repository.ExemplaireRepository;
import bibliotheque.repository.ReservationRepository;
import bibliotheque.repository.StatutReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;
    @Autowired
    private StatutReservationRepository statutReservationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(int id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteById(int id) {
        reservationRepository.deleteById(id);
    }


    public String reserverExemplaire(int idAdherent, int idExemplaire, Date dateReservation) {
        // Règle 1: adhérent existe
        Adherent adherent = adherentRepository.findById(idAdherent)
                .orElse(null);
        if (adherent == null) return "Adhérent introuvable.";

        // Règle 2: abonnement valide
        if (!abonnementRepository.findValidByAdherentId(idAdherent).isPresent())
            return "Aucun abonnement valide pour cet adhérent.";

        // Règle 3: exemplaire existe
        Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
                .orElse(null);
        if (exemplaire == null) return "Exemplaire introuvable.";

        // Règle 4: exemplaire pas réservé/à prêter à cette date
        if (!reservationRepository.findByExemplaireAndDate(idExemplaire, dateReservation).isEmpty())
            return "Exemplaire déjà réservé à cette date.";

        // Règle 4b: quota de réservation
        int nbResa = reservationRepository.countPendingByAdherentId(idAdherent);
        if (nbResa >= adherent.getTypeAdherent().getNbReservationMax())
            return "Quota de réservations atteint.";

        // Statut = "en attente de validation"
        StatutReservation statut = statutReservationRepository.findByLibelle("en attente de validation")
                .orElseThrow(() -> new RuntimeException("Statut 'en attente de validation' non trouvé."));

        // Création de la réservation
        Reservation resa = new Reservation();
        resa.setAdherent(adherent);
        resa.setExemplaire(exemplaire);
        resa.setDateReservation(dateReservation);
        resa.setStatutReservation(statut);

        reservationRepository.save(resa);
        return "success";
    }

    public List<Reservation> getReservationsEnAttente() {
        return reservationRepository.findAllPending();
    }


    public String validerReservation(int idReservation, boolean valider) {
        Reservation resa = reservationRepository.findById(idReservation).orElse(null);
        if (resa == null) return "Réservation introuvable.";

        if (valider) {
            StatutReservation statutValide = statutReservationRepository.findByLibelle("valide")
                    .orElseThrow(() -> new RuntimeException("Statut 'valide' non trouvé."));
            resa.setStatutReservation(statutValide);
            reservationRepository.save(resa);
            // Ici tu peux ajouter la logique de transformation en prêt, si besoin
            return "Réservation validée, prêt à effectuer.";
        } else {
            StatutReservation statutRefuse = statutReservationRepository.findByLibelle("refuse")
                    .orElseThrow(() -> new RuntimeException("Statut 'refuse' non trouvé."));
            resa.setStatutReservation(statutRefuse);
            reservationRepository.save(resa);
            return "Réservation refusée.";
        }
    }

}