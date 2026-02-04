package thesis.Graduation.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import thesis.Graduation.thesis.entity.Salon;
import thesis.Graduation.thesis.service.SalonService;

import java.util.List;

@Controller
public class SalonController {

    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping("/salon")
    public String salonList(Model model) {
        List<Salon> salons = salonService.getAllSalons();
        model.addAttribute("salons", salons);
        return "salon-list";
    }

    @GetMapping("/salon/{id}")
    public String salonDetail(@PathVariable Long id, Model model) {
        try {
            Salon salon = salonService.getSalonById(id);
            model.addAttribute("salon", salon);


            int carsCount = salon.getCars() != null ? salon.getCars().size() : 0;
            model.addAttribute("carsCount", carsCount);

            return "salon-detail";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Nie znaleziono salonu o podanym ID.");
            return "error";
        }
    }
}
