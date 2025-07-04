package bibliotheque.repository;

import bibliotheque.entity.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatutReservationRepository extends JpaRepository<StatutReservation, Integer> {
    Optional<StatutReservation> findByLibelle(String libelle);
}