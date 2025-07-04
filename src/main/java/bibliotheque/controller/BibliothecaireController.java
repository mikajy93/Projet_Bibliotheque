package bibliotheque.controller;

import bibliotheque.entity.Abonnement;
import bibliotheque.entity.Adherent;
import bibliotheque.entity.Pret;
import bibliotheque.service.AbonnementService;
import bibliotheque.service.AuthService;
import bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bibliothecaires")
public class BibliothecaireController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("/accueil")
    public String showBibliothecaireAccueil(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String userRole = (String) session.getAttribute("userRole");
        if (userId == null || userName == null || !"bibliothecaire".equals(userRole)) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        model.addAttribute("userName", userName);
        // model.addAttribute("prets", authService.getHistoriquePrets(null, null)); // Récupère tous les prêts
        return "bibliothecaire_accueil";
    }

    @PostMapping("/abonnements")
    public String createAbonnement(@RequestParam("idAdherent") int idAdherent,
                                  @RequestParam("dateDebut") String dateDebut,
                                  @RequestParam("dateFin") String dateFin,
                                  Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("userRole");
        if (userId == null || !"bibliothecaire".equals(userRole)) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }

        try {
            Optional<Adherent> adherentOpt = adherentRepository.findById(idAdherent);
            if (!adherentOpt.isPresent()) {
                model.addAttribute("error", "Adhérent non trouvé.");
                // model.addAttribute("prets", authService.getHistoriquePrets(null, null));
                model.addAttribute("userName", session.getAttribute("userName"));
                return "bibliothecaire_accueil";
            }

            // Optional<Abonnement> existingAbonnement = abonnementService.findValidByAdherentId(idAdherent);
            if (existingAbonnement.isPresent()) {
                model.addAttribute("error", "Cet adhérent a déjà un abonnement valide.");
                // model.addAttribute("prets", authService.getHistoriquePrets(null, null));
                model.addAttribute("userName", session.getAttribute("userName"));
                return "bibliothecaire_accueil";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(dateDebut);
            Date endDate = dateFormat.parse(dateFin);

            if (startDate.after(endDate)) {
                model.addAttribute("error", "La date de début doit être antérieure à la date de fin.");
                // model.addAttribute("prets", authService.getHistoriquePrets(null, null));
                model.addAttribute("userName", session.getAttribute("userName"));
                return "bibliothecaire_accueil";
            }

            Abonnement abonnement = new Abonnement();
            abonnement.setAdherent(adherentOpt.get());
            abonnement.setDateDebut(startDate);
            abonnement.setDateFin(endDate);
            abonnementService.save(abonnement);

            model.addAttribute("success", "Abonnement créé avec succès.");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la création de l'abonnement : " + e.getMessage());
        }

        // model.addAttribute("prets", authService.getHistoriquePrets(null, null));
        model.addAttribute("userName", session.getAttribute("userName"));
        return "bibliothecaire_accueil";
    }
    @GetMapping("/accueil")
public String showBibliothecaireAccueil(@RequestParam(value = "idAdherent", required = false) Integer idAdherent,
                                       @RequestParam(value = "enCoursSeulement", required = false) Boolean enCoursSeulement,
                                       Model model, HttpSession session) {
    Integer userId = (Integer) session.getAttribute("userId");
    String userName = (String) session.getAttribute("userName");
    String userRole = (String) session.getAttribute("userRole");
    if (userId == null || userName == null || !"bibliothecaire".equals(userRole)) {
        String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return "redirect:" + contextPath + "/";
    }
    model.addAttribute("userName", userName);
    // model.addAttribute("prets", authService.getHistoriquePrets(idAdherent, enCoursSeulement));
    return "bibliothecaire_accueil";
}
}