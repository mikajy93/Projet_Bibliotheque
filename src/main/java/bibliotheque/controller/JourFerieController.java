package bibliotheque.controller;

import bibliotheque.entity.JourFerie;
import bibliotheque.service.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jours-feries")
public class JourFerieController {

    @Autowired
    private JourFerieService jourFerieService;

    @GetMapping
    public List<JourFerie> getAllJoursFeries() {
        return jourFerieService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<JourFerie> getJourFerieById(@PathVariable int id) {
        return jourFerieService.findById(id);
    }

    @PostMapping
    public JourFerie createJourFerie(@RequestBody JourFerie jourFerie) {
        return jourFerieService.save(jourFerie);
    }

    @PutMapping("/{id}")
    public JourFerie updateJourFerie(@PathVariable int id, @RequestBody JourFerie jourFerie) {
        return jourFerieService.save(jourFerie);
    }

    @DeleteMapping("/{id}")
    public void deleteJourFerie(@PathVariable int id) {
        jourFerieService.deleteById(id);
    }
}