package bibliotheque.controller;

import bibliotheque.entity.StatutReservation;
import bibliotheque.service.StatutReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/statuts-reservation")
public class StatutReservationController {

    @Autowired
    private StatutReservationService statutReservationService;

    @GetMapping
    public List<StatutReservation> getAllStatutsReservation() {
        return statutReservationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StatutReservation> getStatutReservationById(@PathVariable int id) {
        return statutReservationService.findById(id);
    }

    @PostMapping
    public StatutReservation createStatutReservation(@RequestBody StatutReservation statutReservation) {
        return statutReservationService.save(statutReservation);
    }

    @PutMapping("/{id}")
    public StatutReservation updateStatutReservation(@PathVariable int id, @RequestBody StatutReservation statutReservation) {
        return statutReservationService.save(statutReservation);
    }

    @DeleteMapping("/{id}")
    public void deleteStatutReservation(@PathVariable int id) {
        statutReservationService.deleteById(id);
    }
}