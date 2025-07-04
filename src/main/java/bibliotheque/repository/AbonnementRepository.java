package bibliotheque.repository;

import bibliotheque.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    @Query("SELECT a FROM Abonnement a WHERE a.adherent.id_adherent = :idAdherent AND a.dateFin >= :currentDate")
    Optional<Abonnement> findValidByAdherentId(int idAdherent, Date currentDate);
}