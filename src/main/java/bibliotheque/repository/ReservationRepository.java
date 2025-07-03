package bibliotheque.repository;

import bibliotheque.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Nombre de réservations actives pour un adhérent

}