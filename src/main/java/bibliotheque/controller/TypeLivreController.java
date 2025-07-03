package bibliotheque.controller;

import bibliotheque.entity.TypeLivre;
import bibliotheque.service.TypeLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/types-livres")
public class TypeLivreController {

    @Autowired
    private TypeLivreService typeLivreService;

    @GetMapping
    public List<TypeLivre> getAllTypesLivres() {
        return typeLivreService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TypeLivre> getTypeLivreById(@PathVariable int id) {
        return typeLivreService.findById(id);
    }

    @PostMapping
    public TypeLivre createTypeLivre(@RequestBody TypeLivre typeLivre) {
        return typeLivreService.save(typeLivre);
    }

    @PutMapping("/{id}")
    public TypeLivre updateTypeLivre(@PathVariable int id, @RequestBody TypeLivre typeLivre) {
        return typeLivreService.save(typeLivre);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeLivre(@PathVariable int id) {
        typeLivreService.deleteById(id);
    }
}