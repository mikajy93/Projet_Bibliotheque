package bibliotheque.controller;

import bibliotheque.entity.Adherent;
import bibliotheque.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Contrôleur REST pour les API (JSON)
@RestController
@RequestMapping("/adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    // GET /adherents (liste JSON)
    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentService.findAll();
    }

    // GET /adherents/{id} (JSON)
    @GetMapping("/{id}")
    public Optional<Adherent> getAdherentById(@PathVariable int id) {
        return adherentService.findById(id);
    }

    // POST /adherents (création via JSON, API REST uniquement)
    @PostMapping
    public Adherent createAdherent(@ModelAttribute("adherent") Adherent adherent) {
        return adherentService.save(adherent);
    }

    // PUT /adherents/{id} (modification via JSON, API REST uniquement)
    @PutMapping("/{id}")
    public Adherent updateAdherent(@PathVariable int id, @RequestBody Adherent adherent) {
        adherent.setId_adherent(id);
        return adherentService.save(adherent);
    }

    // DELETE /adherents/{id}
    @DeleteMapping("/{id}")
    public void deleteAdherent(@PathVariable int id) {
        adherentService.deleteById(id);
    }
}