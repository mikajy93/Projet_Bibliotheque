package bibliotheque.controller;

import bibliotheque.entity.Adherent;
import bibliotheque.repository.ExemplaireRepository;
import bibliotheque.repository.LivreRepository;
import bibliotheque.repository.TypeAdherentRepository;
import bibliotheque.service.AdherentService;
import bibliotheque.service.AuthService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Autowired
    private AdherentService adherentService;

    @GetMapping({"/", "/auth/login"})
    public String showLoginForm(Model model) {
        return "login";
    }
    
    @PostMapping("/auth/login")
    public String processLogin(@RequestParam("email") String email, 
                              @RequestParam("motDePasse") String motDePasse, 
                              Model model, 
                              HttpSession session) {
        try {
            String role = authService.authenticate(email, motDePasse, session);
            if ("adherent".equals(role)) {
                return "redirect:/adherent/accueil";
            } else if ("bibliothecaire".equals(role)) {
                return "redirect:/bibliothecaire/accueil";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "login";
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("typesAdherent", typeAdherentRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("adherent") Adherent adherent,
                                 @RequestParam("idTypeAdherent") int idTypeAdherent,
                                 Model model) {
        try {
            if (adherentService.emailExiste(adherent.getEmail())) {
                model.addAttribute("error", "Cet email est déjà utilisé.");
                model.addAttribute("typesAdherent", typeAdherentRepository.findAll());
                return "register";
            }
            adherentService.inscrireAdherent(adherent, idTypeAdherent);
            model.addAttribute("success", "Inscription réussie, vous pouvez vous connecter.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("typesAdherent", typeAdherentRepository.findAll());
            return "register";
        }
    }



    @GetMapping("/adherent/accueil")
    public String showAdherentAccueil(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        if (userId == null || userName == null || !"adherent".equals(session.getAttribute("userRole"))) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        model.addAttribute("userName", userName);
        model.addAttribute("livres", livreRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "adherent_accueil";
    }

    @PostMapping("/adherent/emprunter")
    public String emprunterLivre(@RequestParam("idExemplaire") int idExemplaire, 
                                 @RequestParam("idTypePret") int idTypePret, 
                                 HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || !"adherent".equals(session.getAttribute("userRole"))) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        try {
            authService.emprunterLivre(userId, idExemplaire, idTypePret);
            model.addAttribute("success", "Livre emprunté avec succès.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            if (e.getMessage().contains("réserv")) {
                model.addAttribute("reservationPossible", true);
            }
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("livres", livreRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "adherent_accueil";
    }

    // @PostMapping("/adherent/reserver")
    // public String reserverLivre(@RequestParam("idLivre") int idLivre, HttpSession session, Model model) {
    //     Integer userId = (Integer) session.getAttribute("userId");
    //     if (userId == null || !"adherent".equals(session.getAttribute("userRole"))) {
    //         String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    //         return "redirect:" + contextPath + "/";
    //     }
    //     try {
    //         authService.reserverLivre(idLivre, userId);
    //         model.addAttribute("success", "Livre réservé avec succès.");
    //     } catch (RuntimeException e) {
    //         model.addAttribute("error", e.getMessage());
    //     }
    //     model.addAttribute("userName", session.getAttribute("userName"));
    //     model.addAttribute("livres", livreRepository.findAll());
    //     model.addAttribute("exemplaires", exemplaireRepository.findAll());
    //     return "adherent_accueil";
    // }

    @GetMapping("/bibliothecaire/accueil")
    public String showBibliothecaireAccueil(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        if (userId == null || userName == null || !"bibliothecaire".equals(session.getAttribute("userRole"))) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        model.addAttribute("userName", userName);
        model.addAttribute("prets", authService.getHistoriquePrets());
        return "bibliothecaire_accueil";
    }
}