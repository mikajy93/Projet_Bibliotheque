package bibliotheque.service;

import bibliotheque.entity.StatutReservation;
import bibliotheque.repository.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutReservationService {

    @Autowired
    private StatutReservationRepository statutReservationRepository;

    public List<StatutReservation> findAll() {
        return statutReservationRepository.findAll();
    }

    public Optional<StatutReservation> findById(int id) {
        return statutReservationRepository.findById(id);
    }

    public StatutReservation save(StatutReservation statutReservation) {
        return statutReservationRepository.save(statutReservation);
    }

    public void deleteById(int id) {
        statutReservationRepository.deleteById(id);
    }
}