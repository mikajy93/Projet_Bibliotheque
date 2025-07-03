package bibliotheque.repository;

import bibliotheque.entity.TypeLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLivreRepository extends JpaRepository<TypeLivre, Integer> {
}