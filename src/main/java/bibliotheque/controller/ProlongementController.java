package bibliotheque.controller;

import bibliotheque.entity.Prolongement;
import bibliotheque.service.ProlongementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/prolongements")
public class ProlongementController {

    @Autowired
    private ProlongementService prolongementService;

    @GetMapping
    public List<Prolongement> getAllProlongements() {
        return prolongementService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Prolongement> getProlongementById(@PathVariable int id) {
        return prolongementService.findById(id);
    }

    @PostMapping
    public Prolongement createProlongement(@RequestBody Prolongement prolongement) {
        return prolongementService.save(prolongement);
    }

    @PutMapping("/{id}")
    public Prolongement updateProlongement(@PathVariable int id, @RequestBody Prolongement prolongement) {
        return prolongementService.save(prolongement);
    }

    @DeleteMapping("/{id}")
    public void deleteProlongement(@PathVariable int id) {
        prolongementService.deleteById(id);
    }
}