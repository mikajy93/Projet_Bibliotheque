package bibliotheque.controller;

import bibliotheque.entity.Exemplaire;
import bibliotheque.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exemplaires")
public class ExemplaireController {

    @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Exemplaire> getExemplaireById(@PathVariable int id) {
        return exemplaireService.findById(id);
    }

    @PostMapping
    public Exemplaire createExemplaire(@RequestBody Exemplaire exemplaire) {
        return exemplaireService.save(exemplaire);
    }

    @PutMapping("/{id}")
    public Exemplaire updateExemplaire(@PathVariable int id, @RequestBody Exemplaire exemplaire) {
        return exemplaireService.save(exemplaire);
    }

    @DeleteMapping("/{id}")
    public void deleteExemplaire(@PathVariable int id) {
        exemplaireService.deleteById(id);
    }
}