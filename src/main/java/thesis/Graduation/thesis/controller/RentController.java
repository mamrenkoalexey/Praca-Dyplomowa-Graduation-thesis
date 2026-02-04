package thesis.Graduation.thesis.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Rent;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.RentStatus;
import thesis.Graduation.thesis.service.CarService;
import thesis.Graduation.thesis.service.ClientService;
import thesis.Graduation.thesis.service.EmployeeService;
import thesis.Graduation.thesis.service.RentService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/seller/rents")
@PreAuthorize("hasRole('SELLER')")
public class RentController {

    private final RentService rentService;
    private final CarService carService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    public RentController(RentService rentService,
                          CarService carService,
                          ClientService clientService,
                          EmployeeService employeeService) {
        this.rentService = rentService;
        this.carService = carService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listRents(Model model) {
        List<Rent> rents = rentService.getAllRents();
        model.addAttribute("rents", rents);
        model.addAttribute("totalRents", rents.size());
        model.addAttribute("activeRents", rents.stream()
                .filter(r -> r.getStatus() == RentStatus.ACTIVE).count());
        model.addAttribute("totalRevenue", rentService.getTotalRentRevenue());
        return "seller/rent/list-rents";
    }

    @GetMapping("/new")
    public String newRentForm(Model model) {
        List<Car> availableCars = carService.getAllCars().stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();

        model.addAttribute("cars", availableCars);
        model.addAttribute("clients", clientService.getActiveClients());
        model.addAttribute("employees", employeeService.getActiveEmployees());

        return "seller/rent/new-rent";
    }

    @PostMapping("/new")
    public String createRent(
            @RequestParam Long carId,
            @RequestParam Long clientId,
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam Double dailyRate,
            @RequestParam Double deposit,
            @RequestParam(required = false) String notes,
            RedirectAttributes redirectAttributes) {

        try {
            Rent rent = rentService.createRent(
                    carId, clientId, employeeId,
                    startDate, endDate,
                    dailyRate, deposit,
                    notes
            );
            redirectAttributes.addFlashAttribute("successMessage",
                    "Wynajem został pomyślnie utworzony! Numer: " + rent.getRentNumber());
            return "redirect:/seller/rents/" + rent.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas tworzenia wynajmu: " + e.getMessage());
            return "redirect:/seller/rents/new";
        }
    }

    @GetMapping("/{id}")
    public String viewRent(@PathVariable Long id, Model model) {
        try {
            Rent rent = rentService.getRentById(id);
            model.addAttribute("rent", rent);
            return "seller/rent/rent-detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono wynajmu: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}/complete")
    public String completeRent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            rentService.completeRent(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Wynajem został pomyślnie zakończony.");
            return "redirect:/seller/rents/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas kończenia wynajmu: " + e.getMessage());
            return "redirect:/seller/rents/" + id;
        }
    }

    @PostMapping("/{id}/complete-late")
    public String completeRentLate(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate actualReturnDate,
            RedirectAttributes redirectAttributes) {
        try {
            rentService.completeRentWithLateReturn(id, actualReturnDate);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Wynajem został zakończony ze spóźnionym zwrotem.");
            return "redirect:/seller/rents/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas kończenia wynajmu: " + e.getMessage());
            return "redirect:/seller/rents/" + id;
        }
    }

    @PostMapping("/{id}/cancel")
    public String cancelRent(
            @PathVariable Long id,
            @RequestParam String reason,
            RedirectAttributes redirectAttributes) {
        try {
            rentService.cancelRent(id, reason);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Wynajem został pomyślnie anulowany.");
            return "redirect:/seller/rents/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas anulowania wynajmu: " + e.getMessage());
            return "redirect:/seller/rents/" + id;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteRent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            rentService.deleteRent(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Wynajem został pomyślnie usunięty.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas usuwania wynajmu: " + e.getMessage());
        }
        return "redirect:/seller/rents";
    }

    @GetMapping("/client/{clientId}")
    public String viewClientRents(@PathVariable Long clientId, Model model) {
        try {
            List<Rent> rents = rentService.getRentsByClient(clientId);
            model.addAttribute("rents", rents);
            model.addAttribute("client", clientService.getClientById(clientId));
            return "seller/rent/client-rents";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono klienta: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employee/{employeeId}")
    public String viewEmployeeRents(@PathVariable Long employeeId, Model model) {
        try {
            List<Rent> rents = rentService.getRentsByEmployee(employeeId);
            model.addAttribute("rents", rents);
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            return "seller/rent/employee-rents";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono pracownika: " + e.getMessage());
            return "error";
        }
    }
}
