package bibliotheque.repository;

import bibliotheque.entity.JourOuvrable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JourOuvrableRepository extends JpaRepository<JourOuvrable, Integer> {
    @Query("SELECT jo.dateJourOuv FROM JourOuvrable jo WHERE jo.dateJourOuv BETWEEN :startDate AND :endDate")
    List<Date> findBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}