package bibliotheque.controller;

import bibliotheque.entity.Retour;
import bibliotheque.service.RetourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/retours")
public class RetourController {

    @Autowired
    private RetourService retourService;

    @GetMapping
    public List<Retour> getAllRetours() {
        return retourService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Retour> getRetourById(@PathVariable int id) {
        return retourService.findById(id);
    }

    @PostMapping
    public Retour createRetour(@RequestBody Retour retour) {
        return retourService.save(retour);
    }

    @PutMapping("/{id}")
    public Retour updateRetour(@PathVariable int id, @RequestBody Retour retour) {
        return retourService.save(retour);
    }

    @DeleteMapping("/{id}")
    public void deleteRetour(@PathVariable int id) {
        retourService.deleteById(id);
    }
}