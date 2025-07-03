package bibliotheque.repository;

import bibliotheque.entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    @Query("SELECT e FROM Exemplaire e WHERE e.livre.id_livre = :idLivre AND e.id_exemplaire NOT IN :idExemplaires")
    Optional<Exemplaire> findFirstAvailableByLivreId(@Param("idLivre") int idLivre, @Param("idExemplaires") List<Integer> idExemplaires);
}