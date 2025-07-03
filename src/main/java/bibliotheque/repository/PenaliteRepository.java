package bibliotheque.repository;

import bibliotheque.entity.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    @Query(value = "SELECT p.* FROM Penalite p " +
                   "JOIN Pret pr ON p.id_pret = pr.id_pret " +
                   "WHERE pr.id_adherent = :idAdherent " +
                   "AND CURRENT_DATE <= DATE_ADD(pr.date_pret, INTERVAL p.duree_penalite DAY)", 
           nativeQuery = true)
    Optional<Penalite> findActiveByAdherentId(@Param("idAdherent") int idAdherent);
}