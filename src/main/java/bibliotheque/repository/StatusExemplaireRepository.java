package bibliotheque.repository;

import bibliotheque.entity.StatusExemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatusExemplaireRepository extends JpaRepository<StatusExemplaire, Integer> {
    @Query("SELECT se FROM StatusExemplaire se WHERE se.exemplaire.id_exemplaire = :idExemplaire ORDER BY se.dateChangement DESC")
    Optional<StatusExemplaire> findLatestByExemplaireId(@Param("idExemplaire") int idExemplaire);
}