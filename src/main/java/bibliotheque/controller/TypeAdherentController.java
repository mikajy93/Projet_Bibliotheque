package bibliotheque.controller;

import bibliotheque.entity.TypeAdherent;
import bibliotheque.service.TypeAdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/types-adherents")
public class TypeAdherentController {

    @Autowired
    private TypeAdherentService typeAdherentService;

    @GetMapping
    public List<TypeAdherent> getAllTypesAdherents() {
        return typeAdherentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TypeAdherent> getTypeAdherentById(@PathVariable int id) {
        return typeAdherentService.findById(id);
    }

    @PostMapping
    public TypeAdherent createTypeAdherent(@RequestBody TypeAdherent typeAdherent) {
        return typeAdherentService.save(typeAdherent);
    }

    @PutMapping("/{id}")
    public TypeAdherent updateTypeAdherent(@PathVariable int id, @RequestBody TypeAdherent typeAdherent) {
        return typeAdherentService.save(typeAdherent);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeAdherent(@PathVariable int id) {
        typeAdherentService.deleteById(id);
    }
}