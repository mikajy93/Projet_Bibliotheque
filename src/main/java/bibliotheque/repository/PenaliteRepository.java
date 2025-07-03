package bibliotheque.repository;

import bibliotheque.entity.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {

}