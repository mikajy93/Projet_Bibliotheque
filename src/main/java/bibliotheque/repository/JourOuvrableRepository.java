package bibliotheque.repository;

import bibliotheque.entity.JourOuvrable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourOuvrableRepository extends JpaRepository<JourOuvrable, Integer> {
}