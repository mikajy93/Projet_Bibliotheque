package bibliotheque.controller;

import bibliotheque.entity.Livre;
import bibliotheque.repository.LivreRepository;
import bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/livres")
public class LivreController {
    
    @Autowired
    private LivreService livreService;
    
    @Autowired
private LivreRepository livreRepository;
    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Livre> getLivreById(@PathVariable int id) {
        return livreService.findById(id);
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.save(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable int id, @RequestBody Livre livre) {
        return livreService.save(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable int id) {
        livreService.deleteById(id);
    }


    @GetMapping("/livres")
    public String afficherListeLivres(Model model) {
        List<Livre> livres = livreRepository.findAll();
        model.addAttribute("livres", livres);
        return "livres"; 
    }

    
}