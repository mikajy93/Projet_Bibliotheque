package bibliotheque.controller;

import bibliotheque.service.ReservationService;
import bibliotheque.entity.Reservation;
import bibliotheque.repository.ExemplaireRepository;
import bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable int id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }





    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteById(id);
    }


    @GetMapping("/reservation")
    public String showForm(Model model, HttpSession session) {
        model.addAttribute("livres", livreRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "reservation_form";
    }

    @PostMapping("/reservation")
    public String reserver(@RequestParam("idExemplaire") int idExemplaire,
                           @RequestParam("dateReservation") String dateStr,
                           HttpSession session,
                           Model model) {
        Integer idAdherent = (Integer) session.getAttribute("userId");
        if (idAdherent == null) {
            model.addAttribute("error", "Vous devez vous connecter.");
            return "reservation_form";
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            String result = reservationService.reserverExemplaire(idAdherent, idExemplaire, date);
            if ("success".equals(result)) {
                model.addAttribute("success", "Réservation demandée, en attente de validation.");
            } else {
                model.addAttribute("error", result);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Erreur de format de date.");
        }
        model.addAttribute("livres", livreRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "reservation_form";
    }

    @PostMapping("/adherent/reserver")
    public String reserver(
        @RequestParam("idExemplaire") int idExemplaire,
        @RequestParam("dateReservation") String dateReservationStr,
        HttpSession session,
        RedirectAttributes redirectAttributes) {
        Integer idAdherent = (Integer) session.getAttribute("userId");
        if (idAdherent == null) {
            redirectAttributes.addFlashAttribute("error", "Vous devez être connecté.");
            return "redirect:/adherent/accueil";
        }
        try {
            Date dateReservation = new SimpleDateFormat("yyyy-MM-dd").parse(dateReservationStr);
            String result = reservationService.reserverExemplaire(idAdherent, idExemplaire, dateReservation);
            if ("success".equals(result)) {
                redirectAttributes.addFlashAttribute("success", "Réservation demandée, en attente de validation.");
            } else {
                redirectAttributes.addFlashAttribute("error", result);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur de format de date.");
        }
        return "redirect:/adherent/accueil";
    }
}