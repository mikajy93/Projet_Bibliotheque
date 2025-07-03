package bibliotheque.repository;

import bibliotheque.entity.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JourFerieRepository extends JpaRepository<JourFerie, Integer> {

}