package bibliotheque.repository;

import bibliotheque.entity.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JourFerieRepository extends JpaRepository<JourFerie, Integer> {
    @Query("SELECT jf.dateJourFerie FROM JourFerie jf WHERE jf.dateJourFerie BETWEEN :startDate AND :endDate")
    List<Date> findBetweenDates(Date startDate, Date endDate);

}