package bibliotheque.repository;

import bibliotheque.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PretRepository extends JpaRepository<Pret, Integer> {

}