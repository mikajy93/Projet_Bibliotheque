package bibliotheque.service;

import bibliotheque.entity.*;
import bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private PenaliteRepository penaliteRepository;

    @Autowired
    private StatusExemplaireRepository statusExemplaireRepository;

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    public String authenticate(String email, String motDePasse, HttpSession session) {
        Adherent adherent = adherentRepository.findByEmail(email);
        if (adherent != null && motDePasse.equals(adherent.getMotDePasse())) {
            session.setAttribute("userId", adherent.getId_adherent());
            session.setAttribute("userName", adherent.getNom());
            session.setAttribute("userRole", "adherent");
            return "adherent";
        }

        Bibliothecaire bibliothecaire = bibliothecaireRepository.findByEmail(email);
        if (bibliothecaire != null && motDePasse.equals(bibliothecaire.getMotDePasse())) {
            session.setAttribute("userId", bibliothecaire.getId_biblio());
            session.setAttribute("userName", bibliothecaire.getNom());
            session.setAttribute("userRole", "bibliothecaire");
            return "bibliothecaire";
        }

        throw new RuntimeException("Identifiant ou mot de passe incorrect.");
    }

    public void emprunterLivre(int idAdherent, int idExemplaire, int idTypePret) {
        // Vérification 1: L'adhérent existe
        Optional<Adherent> adherentOpt = adherentRepository.findById(idAdherent);
        if (!adherentOpt.isPresent()) {
            throw new RuntimeException("Adhérent non trouvé.");
        }
        Adherent adherent = adherentOpt.get();

        // Vérification 2: Abonnement valide
        Optional<Abonnement> abonnementOpt = abonnementRepository.findValidByAdherentId(idAdherent, new Date());
        if (!abonnementOpt.isPresent()) {
            throw new RuntimeException("Aucun abonnement valide pour cet adhérent.");
        }

        // Vérification 3: Exemplaire existe
        Optional<Exemplaire> exemplaireOpt = exemplaireRepository.findById(idExemplaire);
        if (!exemplaireOpt.isPresent()) {
            throw new RuntimeException("Exemplaire non trouvé.");
        }
        Exemplaire exemplaire = exemplaireOpt.get();

        // Vérification 4: Exemplaire disponible et en bon état
        // Optional<StatusExemplaire> statusOpt = statusExemplaireRepository.findLatestByExemplaireId(idExemplaire);
        // if (!statusOpt.isPresent() || !"Disponible".equalsIgnoreCase(statusOpt.get().getEtat().getLibelle())) {
        //     throw new RuntimeException("L'exemplaire n'est pas disponible ou n'est pas en bon état.");
        // }

        // Vérification 5: Aucune pénalité active
        Optional<Penalite> penaliteOpt = penaliteRepository.findActiveByAdherentId(idAdherent);
        if (penaliteOpt.isPresent()) {
            throw new RuntimeException("L'adhérent a une pénalité active.");
        }

        // Vérification 6: Quota individuel non atteint
        if (adherent.getQuotaRestant() == null || adherent.getQuotaRestant() <= 0) {
            throw new RuntimeException("Quota de prêts atteint.");
        }

        // Vérification 7: Âge minimum respecté
        Calendar cal = Calendar.getInstance();
        cal.setTime(adherent.getDateNaissance());
        int age = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (exemplaire.getLivre().getAgeMinimum() > age) {
            throw new RuntimeException("Âge minimum requis pour ce livre non respecté.");
        }

        // Vérification 8: Exemplaire non réservé
        boolean isReserved = reservationRepository.findAll().stream()
                .anyMatch(r -> r.getExemplaire().getId_exemplaire() == idExemplaire &&
                        r.getStatutReservation().getId_statut_reservation() == 1);
        if (isReserved) {
            throw new RuntimeException("L'exemplaire est réservé par un autre adhérent. Essayez de réserver.");
        }

        // Récupérer le TypePret complet via le repository
        TypePret typePret = typePretRepository.findById(idTypePret)
            .orElseThrow(() -> new RuntimeException("Type de prêt non trouvé."));

        // Calcul de la date de prêt (aujourd'hui)
        Date datePret = new Date();
        Date dateRetourPrevue;

        // Calcul de la date de retour prévue selon le type de prêt
        if ("SUR PLACE".equalsIgnoreCase(typePret.getLibelle())) {
            // Prêt sur place : retour le même jour
            dateRetourPrevue = datePret;
        } else {
            // Prêt à domicile : appliquer la logique de calcul classique
            int dureePret = adherent.getTypeAdherent().getDureePret();
            Calendar retourCal = Calendar.getInstance();
            retourCal.setTime(datePret);
            retourCal.add(Calendar.DAY_OF_MONTH, dureePret);
            dateRetourPrevue = retourCal.getTime();

            // Liste des jours fériés entre les deux dates
            List<Date> joursFeries = jourFerieRepository.findBetweenDates(datePret, dateRetourPrevue);

            // Ajustement : si la date de retour prévue tombe sur un jour férié OU un dimanche, décale-la d'un jour jusqu'à tomber sur un jour ouvré
            boolean decale;
            do {
                decale = false;
                // Jour férié
                for (Date jourFerie : joursFeries) {
                    if (isSameDay(jourFerie, dateRetourPrevue)) {
                        retourCal.add(Calendar.DAY_OF_MONTH, 1);
                        dateRetourPrevue = retourCal.getTime();
                        decale = true;
                        break;
                    }
                }
                // Dimanche
                Calendar tmp = Calendar.getInstance();
                tmp.setTime(dateRetourPrevue);
                if (tmp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    retourCal.add(Calendar.DAY_OF_MONTH, 1);
                    dateRetourPrevue = retourCal.getTime();
                    decale = true;
                }
            } while (decale);
        }

        // Enregistrement du prêt
        Pret pret = new Pret();
        pret.setExemplaire(exemplaire);
        pret.setAdherent(adherent);
        pret.setTypePret(typePret);
        pret.setDatePret(datePret);
        pret.setDateRetourPrevue(dateRetourPrevue);
        pretRepository.save(pret);

        // Décrémenter le quota individuel
        adherent.setQuotaRestant(adherent.getQuotaRestant() - 1);
        adherentRepository.save(adherent);

        // Mise à jour du statut de l'exemplaire
        StatusExemplaire statusExemplaire = new StatusExemplaire();
        statusExemplaire.setExemplaire(exemplaire);
        statusExemplaire.setDateChangement(new Date());
        EtatExemplaire etat = new EtatExemplaire();
        etat.setId_etat(2); // Supposons id_etat=2 pour "Emprunté"
        statusExemplaire.setEtat(etat);
        statusExemplaireRepository.save(statusExemplaire);
    }

    private boolean isSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
            && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public List<Pret> getHistoriquePrets() {
        return pretRepository.findAll();
    }
}