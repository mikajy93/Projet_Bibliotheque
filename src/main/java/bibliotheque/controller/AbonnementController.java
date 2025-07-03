package bibliotheque.controller;

import bibliotheque.entity.Abonnement;
import bibliotheque.service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @GetMapping
    public List<Abonnement> getAllAbonnements() {
        return abonnementService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Abonnement> getAbonnementById(@PathVariable int id) {
        return abonnementService.findById(id);
    }

    @PostMapping
    public Abonnement createAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementService.save(abonnement);
    }

    @PutMapping("/{id}")
    public Abonnement updateAbonnement(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return abonnementService.save(abonnement);
    }

    @DeleteMapping("/{id}")
    public void deleteAbonnement(@PathVariable int id) {
        abonnementService.deleteById(id);
    }
}