package bibliotheque.controller;

import bibliotheque.entity.TypePret;
import bibliotheque.service.TypePretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/types-prets")
public class TypePretController {

    @Autowired
    private TypePretService typePretService;

    @GetMapping
    public List<TypePret> getAllTypesPrets() {
        return typePretService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TypePret> getTypePretById(@PathVariable int id) {
        return typePretService.findById(id);
    }

    @PostMapping
    public TypePret createTypePret(@RequestBody TypePret typePret) {
        return typePretService.save(typePret);
    }

    @PutMapping("/{id}")
    public TypePret updateTypePret(@PathVariable int id, @RequestBody TypePret typePret) {
        return typePretService.save(typePret);
    }

    @DeleteMapping("/{id}")
    public void deleteTypePret(@PathVariable int id) {
        typePretService.deleteById(id);
    }
}