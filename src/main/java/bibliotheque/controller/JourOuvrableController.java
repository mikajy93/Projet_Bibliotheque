package bibliotheque.controller;

import bibliotheque.entity.JourOuvrable;
import bibliotheque.service.JourOuvrableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jours-ouvrables")
public class JourOuvrableController {

    @Autowired
    private JourOuvrableService jourOuvrableService;

    @GetMapping
    public List<JourOuvrable> getAllJoursOuvrables() {
        return jourOuvrableService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<JourOuvrable> getJourOuvrableById(@PathVariable int id) {
        return jourOuvrableService.findById(id);
    }

    @PostMapping
    public JourOuvrable createJourOuvrable(@RequestBody JourOuvrable jourOuvrable) {
        return jourOuvrableService.save(jourOuvrable);
    }

    @PutMapping("/{id}")
    public JourOuvrable updateJourOuvrable(@PathVariable int id, @RequestBody JourOuvrable jourOuvrable) {
        return jourOuvrableService.save(jourOuvrable);
    }

    @DeleteMapping("/{id}")
    public void deleteJourOuvrable(@PathVariable int id) {
        jourOuvrableService.deleteById(id);
    }
}