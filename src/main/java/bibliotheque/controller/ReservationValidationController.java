package bibliotheque.controller;

import bibliotheque.entity.Reservation;
import bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bibliothecaire")
public class ReservationValidationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getReservationsEnAttente());
        return "reservation_validation";
    }

    @PostMapping("/reservations/valider")
    public String valider(@RequestParam("idReservation") int idReservation, Model model) {
        String result = reservationService.validerReservation(idReservation, true);
        model.addAttribute("success", result);
        model.addAttribute("reservations", reservationService.getReservationsEnAttente());
        return "reservation_validation";
    }

    @PostMapping("/reservations/refuser")
    public String refuser(@RequestParam("idReservation") int idReservation, Model model) {
        String result = reservationService.validerReservation(idReservation, false);
        model.addAttribute("error", result);
        model.addAttribute("reservations", reservationService.getReservationsEnAttente());
        return "reservation_validation";
    }
}