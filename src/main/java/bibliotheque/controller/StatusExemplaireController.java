package bibliotheque.controller;

import bibliotheque.entity.StatusExemplaire;
import bibliotheque.service.StatusExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/status-exemplaires")
public class StatusExemplaireController {

    @Autowired
    private StatusExemplaireService statusExemplaireService;

    @GetMapping
    public List<StatusExemplaire> getAllStatusExemplaires() {
        return statusExemplaireService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StatusExemplaire> getStatusExemplaireById(@PathVariable int id) {
        return statusExemplaireService.findById(id);
    }

    @PostMapping
    public StatusExemplaire createStatusExemplaire(@RequestBody StatusExemplaire statusExemplaire) {
        return statusExemplaireService.save(statusExemplaire);
    }

    @PutMapping("/{id}")
    public StatusExemplaire updateStatusExemplaire(@PathVariable int id, @RequestBody StatusExemplaire statusExemplaire) {
        return statusExemplaireService.save(statusExemplaire);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusExemplaire(@PathVariable int id) {
        statusExemplaireService.deleteById(id);
    }
}