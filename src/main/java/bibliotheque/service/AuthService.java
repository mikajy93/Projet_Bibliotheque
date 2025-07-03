package bibliotheque.service;

import bibliotheque.entity.*;
import bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public String authenticate(String email, String motDePasse, HttpSession session) {
        // Vérifier dans la table Adherent
        Adherent adherent = adherentRepository.findByEmail(email);
        if (adherent != null && motDePasse.equals(adherent.getMotDePasse())) {
            session.setAttribute("userId", adherent.getId_adherent());
            session.setAttribute("userName", adherent.getNom());
            session.setAttribute("userRole", "adherent");
            return "adherent";
        }

        // Vérifier dans la table Bibliothecaire
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findByEmail(email);
        if (bibliothecaire != null && motDePasse.equals(bibliothecaire.getMotDePasse())) {
            session.setAttribute("userId", bibliothecaire.getId_biblio());
            session.setAttribute("userName", bibliothecaire.getNom());
            session.setAttribute("userRole", "bibliothecaire");
            return "bibliothecaire";
        }

        // Si aucun utilisateur n'est trouvé ou mot de passe incorrect
        throw new RuntimeException("Identifiant ou mot de passe incorrect.");
    }

    public void emprunterLivre(int idLivre, int idAdherent) {
        // Trouver un exemplaire disponible (non prêté)
        Exemplaire exemplaire = exemplaireRepository.findFirstAvailableByLivreId(
            idLivre, pretRepository.findAll().stream()
                .filter(p -> p.getDateRetourReelle() == null)
                .map(Pret::getExemplaire)
                .map(Exemplaire::getId_exemplaire)
                .toList()
        ).orElseThrow(() -> new RuntimeException("Aucun exemplaire disponible pour ce livre."));

        // Créer un prêt
        Pret pret = new Pret();
        pret.setExemplaire(exemplaire);
        pret.setAdherent(adherentRepository.findById(idAdherent).orElseThrow());
        pret.setTypePret(new TypePret()); // Supposons id_type_pret = 1 pour simplifier
        pret.getTypePret().setId_type_pret(1);
        pret.setDatePret(new Date());
        pret.setDateRetourPrevue(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)); // 14 jours
        pretRepository.save(pret);
    }

    public void reserverLivre(int idLivre, int idAdherent) {
        // Trouver un exemplaire disponible (non réservé)
        Exemplaire exemplaire = exemplaireRepository.findFirstAvailableByLivreId(
            idLivre, reservationRepository.findAll().stream()
                .filter(r -> r.getStatutReservation().getId_statut_reservation() == 1) // Statut "en attente"
                .map(Reservation::getExemplaire)
                .map(Exemplaire::getId_exemplaire)
                .toList()
        ).orElseThrow(() -> new RuntimeException("Aucun exemplaire disponible pour réservation."));

        // Créer une réservation
        Reservation reservation = new Reservation();
        reservation.setExemplaire(exemplaire);
        reservation.setAdherent(adherentRepository.findById(idAdherent).orElseThrow());
        reservation.setStatutReservation(new StatutReservation()); // Supposons id_statut_reservation = 1
        reservation.getStatutReservation().setId_statut_reservation(1);
        reservation.setDateReservation(new Date());
        reservationRepository.save(reservation);
    }
}