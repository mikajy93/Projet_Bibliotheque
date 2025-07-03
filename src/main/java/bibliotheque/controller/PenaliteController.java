package bibliotheque.controller;

import bibliotheque.entity.Penalite;
import bibliotheque.service.PenaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/penalites")
public class PenaliteController {

    @Autowired
    private PenaliteService penaliteService;

    @GetMapping
    public List<Penalite> getAllPenalites() {
        return penaliteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Penalite> getPenaliteById(@PathVariable int id) {
        return penaliteService.findById(id);
    }

    @PostMapping
    public Penalite createPenalite(@RequestBody Penalite penalite) {
        return penaliteService.save(penalite);
    }

    @PutMapping("/{id}")
    public Penalite updatePenalite(@PathVariable int id, @RequestBody Penalite penalite) {
        return penaliteService.save(penalite);
    }

    @DeleteMapping("/{id}")
    public void deletePenalite(@PathVariable int id) {
        penaliteService.deleteById(id);
    }
}