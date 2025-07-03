package bibliotheque.controller;

import bibliotheque.entity.Adherent;
import bibliotheque.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Adherent> getAdherentById(@PathVariable int id) {
        return adherentService.findById(id);
    }

    @PostMapping
    public Adherent createAdherent(@RequestBody Adherent adherent) {
        return adherentService.save(adherent);
    }

    @PutMapping("/{id}")
    public Adherent updateAdherent(@PathVariable int id, @RequestBody Adherent adherent) {
        return adherentService.save(adherent);
    }

    @DeleteMapping("/{id}")
    public void deleteAdherent(@PathVariable int id) {
        adherentService.deleteById(id);
    }
}