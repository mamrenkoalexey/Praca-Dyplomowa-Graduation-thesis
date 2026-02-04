package thesis.Graduation.thesis.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Lease;
import thesis.Graduation.thesis.entity.Payment;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.LeaseStatus;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.service.CarService;
import thesis.Graduation.thesis.service.ClientService;
import thesis.Graduation.thesis.service.EmployeeService;
import thesis.Graduation.thesis.service.LeaseService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/seller/leases")
@PreAuthorize("hasRole('SELLER')")
public class LeaseController {

    private final LeaseService leaseService;
    private final CarService carService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    public LeaseController(LeaseService leaseService,
                          CarService carService,
                          ClientService clientService,
                          EmployeeService employeeService) {
        this.leaseService = leaseService;
        this.carService = carService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }


    @GetMapping
    public String listLeases(Model model) {
        List<Lease> leases = leaseService.getAllLeases();
        model.addAttribute("leases", leases);
        model.addAttribute("totalLeases", leases.size());
        model.addAttribute("activeLeases", leases.stream()
                .filter(l -> l.getStatus() == LeaseStatus.ACTIVE).count());
        model.addAttribute("totalRevenue", leaseService.getTotalLeaseRevenue());
        return "seller/lease/list-leases";
    }

    /**
     * Show new lease form
     */
    @GetMapping("/new")
    public String newLeaseForm(Model model) {
        // Get only available cars
        List<Car> availableCars = carService.getAllCars().stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();

        model.addAttribute("cars", availableCars);
        model.addAttribute("clients", clientService.getActiveClients());
        model.addAttribute("employees", employeeService.getActiveEmployees());

        return "seller/lease/new-lease";
    }

    /**
     * Create new lease
     */
    @PostMapping("/new")
    public String createLease(
            @RequestParam Long carId,
            @RequestParam Long clientId,
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam Double initialPayment,
            @RequestParam Double monthlyPayment,
            @RequestParam(required = false) String notes,
            RedirectAttributes redirectAttributes) {

        try {
            Lease lease = leaseService.createLease(
                    carId, clientId, employeeId,
                    startDate, endDate,
                    initialPayment, monthlyPayment,
                    notes
            );
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leasing został pomyślnie utworzony! Numer umowy: " + lease.getContractNumber());
            return "redirect:/seller/leases/" + lease.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas tworzenia leasingu: " + e.getMessage());
            return "redirect:/seller/leases/new";
        }
    }


    @GetMapping("/{id}")
    public String viewLease(@PathVariable Long id, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(id);
            model.addAttribute("lease", lease);
            model.addAttribute("pendingPayments", leaseService.getPendingPayments(id));
            model.addAttribute("completedPayments", leaseService.getCompletedPayments(id));
            model.addAttribute("paymentMethods", PaymentMethod.values());
            return "seller/lease/lease-detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono leasingu: " + e.getMessage());
            return "error";
        }
    }


    @PostMapping("/{id}/activate")
    public String activateLease(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            leaseService.activateLease(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leasing został pomyślnie aktywowany.");
            return "redirect:/seller/leases/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas aktywacji leasingu: " + e.getMessage());
            return "redirect:/seller/leases/" + id;
        }
    }

    /**
     * Complete lease
     */
    @PostMapping("/{id}/complete")
    public String completeLease(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            leaseService.completeLease(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leasing został pomyślnie zakończony.");
            return "redirect:/seller/leases/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas kończenia leasingu: " + e.getMessage());
            return "redirect:/seller/leases/" + id;
        }
    }

    /**
     * Terminate lease
     */
    @PostMapping("/{id}/terminate")
    public String terminateLease(
            @PathVariable Long id,
            @RequestParam String reason,
            RedirectAttributes redirectAttributes) {
        try {
            leaseService.terminateLease(id, reason);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leasing został pomyślnie rozwiązany.");
            return "redirect:/seller/leases/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas rozwiązywania leasingu: " + e.getMessage());
            return "redirect:/seller/leases/" + id;
        }
    }

    /**
     * Process payment
     */
    @PostMapping("/{leaseId}/payments/{paymentId}/process")
    public String processPayment(
            @PathVariable Long leaseId,
            @PathVariable Long paymentId,
            @RequestParam PaymentMethod method,
            RedirectAttributes redirectAttributes) {
        try {
            leaseService.processPayment(leaseId, paymentId, method);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Płatność została pomyślnie zrealizowana.");
            return "redirect:/seller/leases/" + leaseId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas przetwarzania płatności: " + e.getMessage());
            return "redirect:/seller/leases/" + leaseId;
        }
    }

    /**
     * Delete lease
     */
    @DeleteMapping("/{id}")
    public String deleteLease(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            leaseService.deleteLease(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leasing został pomyślnie usunięty.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas usuwania leasingu: " + e.getMessage());
        }
        return "redirect:/seller/leases";
    }

    @GetMapping("/client/{clientId}")
    public String viewClientLeases(@PathVariable Long clientId, Model model) {
        try {
            List<Lease> leases = leaseService.getLeasesByClient(clientId);
            model.addAttribute("leases", leases);
            model.addAttribute("client", clientService.getClientById(clientId));
            return "seller/lease/client-leases";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono klienta: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employee/{employeeId}")
    public String viewEmployeeLeases(@PathVariable Long employeeId, Model model) {
        try {
            List<Lease> leases = leaseService.getLeasesByEmployee(employeeId);
            model.addAttribute("leases", leases);
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            return "seller/lease/employee-leases";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie znaleziono pracownika: " + e.getMessage());
            return "error";
        }
    }
}
