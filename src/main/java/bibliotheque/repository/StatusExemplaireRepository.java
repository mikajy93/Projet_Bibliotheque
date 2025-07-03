package bibliotheque.repository;

import bibliotheque.entity.StatusExemplaire;
import bibliotheque.entity.Exemplaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusExemplaireRepository extends JpaRepository<StatusExemplaire, Integer> {

}