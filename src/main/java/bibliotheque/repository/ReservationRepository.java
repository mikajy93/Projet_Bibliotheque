package bibliotheque.repository;

import bibliotheque.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.adherent.id_adherent = ?1 AND r.statutReservation.libelle = 'en attente de validation'")
    List<Reservation> findPendingByAdherentId(int idAdherent);

    @Query("SELECT r FROM Reservation r WHERE r.statutReservation.libelle = 'en attente de validation'")
    List<Reservation> findAllPending();

    @Query("SELECT r FROM Reservation r WHERE r.exemplaire.id_exemplaire = ?1 AND r.dateReservation = ?2")
    List<Reservation> findByExemplaireAndDate(int idExemplaire, Date dateReservation);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.adherent.id_adherent = ?1 AND r.statutReservation.libelle = 'en attente de validation'")
    int countPendingByAdherentId(int idAdherent);
}