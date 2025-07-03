package bibliotheque.controller;

import bibliotheque.entity.EtatExemplaire;
import bibliotheque.service.EtatExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/etats-exemplaires")
public class EtatExemplaireController {

    @Autowired
    private EtatExemplaireService etatExemplaireService;

    @GetMapping
    public List<EtatExemplaire> getAllEtatsExemplaires() {
        return etatExemplaireService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EtatExemplaire> getEtatExemplaireById(@PathVariable int id) {
        return etatExemplaireService.findById(id);
    }

    @PostMapping
    public EtatExemplaire createEtatExemplaire(@RequestBody EtatExemplaire etatExemplaire) {
        return etatExemplaireService.save(etatExemplaire);
    }

    @PutMapping("/{id}")
    public EtatExemplaire updateEtatExemplaire(@PathVariable int id, @RequestBody EtatExemplaire etatExemplaire) {
        return etatExemplaireService.save(etatExemplaire);
    }

    @DeleteMapping("/{id}")
    public void deleteEtatExemplaire(@PathVariable int id) {
        etatExemplaireService.deleteById(id);
    }
}