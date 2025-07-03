package bibliotheque.controller;

import bibliotheque.entity.Auteur;
import bibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Auteur> getAuteurById(@PathVariable int id) {
        return auteurService.findById(id);
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurService.save(auteur);
    }

    @PutMapping("/{id}")
    public Auteur updateAuteur(@PathVariable int id, @RequestBody Auteur auteur) {
        return auteurService.save(auteur);
    }

    @DeleteMapping("/{id}")
    public void deleteAuteur(@PathVariable int id) {
        auteurService.deleteById(id);
    }
}