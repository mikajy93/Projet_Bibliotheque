package bibliotheque.repository;

import bibliotheque.entity.Retour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetourRepository extends JpaRepository<Retour, Integer> {
}