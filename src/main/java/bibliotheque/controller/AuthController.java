package bibliotheque.controller;

import bibliotheque.entity.Livre;
import bibliotheque.repository.LivreRepository;
import bibliotheque.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping({"/", "/auth/login"})
    public String showLoginForm(Model model) {
        return "login"; // Retourne la vue JSP (WEB-INF/views/login.jsp)
    }

    @PostMapping("/auth/login")
    public String processLogin(@RequestParam("email") String email, 
                              @RequestParam("motDePasse") String motDePasse, 
                              Model model, 
                              HttpSession session) {
        try {
            String role = authService.authenticate(email, motDePasse, session);
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            if ("adherent".equals(role)) {
                return "redirect:" + contextPath + "/adherent/accueil";
            } else if ("bibliothecaire".equals(role)) {
                return "redirect:" + contextPath + "/bibliothecaire/accueil";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "login"; // Retourne la vue JSP avec le message d'erreur
        }
        return "login"; // Fallback (ne devrait pas arriver)
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
        return "adherent_accueil"; // Retourne la vue JSP (WEB-INF/views/adherent_accueil.jsp)
    }

    @PostMapping("/adherent/emprunter")
    public String emprunterLivre(@RequestParam("idLivre") int idLivre, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || !"adherent".equals(session.getAttribute("userRole"))) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        try {
            authService.emprunterLivre(idLivre, userId);
            model.addAttribute("success", "Livre emprunté avec succès.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("livres", livreRepository.findAll());
        return "adherent_accueil";
    }

    @PostMapping("/adherent/reserver")
    public String reserverLivre(@RequestParam("idLivre") int idLivre, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || !"adherent".equals(session.getAttribute("userRole"))) {
            String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return "redirect:" + contextPath + "/";
        }
        try {
            authService.reserverLivre(idLivre, userId);
            model.addAttribute("success", "Livre réservé avec succès.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("livres", livreRepository.findAll());
        return "adherent_accueil";
    }
}