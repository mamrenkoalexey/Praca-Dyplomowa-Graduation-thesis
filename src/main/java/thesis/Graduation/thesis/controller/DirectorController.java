package thesis.Graduation.thesis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thesis.Graduation.thesis.entity.Employee;
import thesis.Graduation.thesis.entity.enums.Role;
import thesis.Graduation.thesis.service.*;

@Controller
@RequestMapping("/director")
@PreAuthorize("hasRole('DIRECTOR')")
public class DirectorController {

    private final EmployeeService employeeService;
    private final SalonService salonService;
    private final SaleService saleService;
    private final LeaseService leaseService;
    private final RentService rentService;
    private final ClientService clientService;

    public DirectorController(EmployeeService employeeService, SalonService salonService,
                              SaleService saleService, LeaseService leaseService,
                              RentService rentService, ClientService clientService) {
        this.employeeService = employeeService;
        this.salonService = salonService;
        this.saleService = saleService;
        this.leaseService = leaseService;
        this.rentService = rentService;
        this.clientService = clientService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalEmployees = employeeService.countEmployees();
        long activeEmployees = employeeService.countActiveEmployees();

        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("activeEmployees", activeEmployees);

        return "director/dashboard";
    }

    // ==================== REPORTS ====================

    @GetMapping("/reports")
    public String reportsOverview(Model model) {
        // Sales stats
        long totalSales = saleService.countSales();
        Double salesRevenue = saleService.getTotalRevenue();

        // Lease stats
        long totalLeases = leaseService.getAllLeases().size();
        long activeLeases = leaseService.getActiveLeases().size();
        Double leaseRevenue = leaseService.getTotalLeaseRevenue();

        // Rent stats
        long totalRents = rentService.countRents();
        long activeRents = rentService.countActiveRents();
        Double rentRevenue = rentService.getTotalRentRevenue();

        // Total revenue
        double totalRevenue = (salesRevenue != null ? salesRevenue : 0.0)
                + (leaseRevenue != null ? leaseRevenue : 0.0)
                + (rentRevenue != null ? rentRevenue : 0.0);

        // Other stats
        long totalClients = clientService.countClients();
        long totalEmployeesCount = employeeService.countEmployees();

        model.addAttribute("totalSales", totalSales);
        model.addAttribute("salesRevenue", salesRevenue != null ? salesRevenue : 0.0);
        model.addAttribute("totalLeases", totalLeases);
        model.addAttribute("activeLeases", activeLeases);
        model.addAttribute("leaseRevenue", leaseRevenue != null ? leaseRevenue : 0.0);
        model.addAttribute("totalRents", totalRents);
        model.addAttribute("activeRents", activeRents);
        model.addAttribute("rentRevenue", rentRevenue != null ? rentRevenue : 0.0);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalEmployees", totalEmployeesCount);

        return "director/reports";
    }

    @GetMapping("/reports/sales")
    public String salesReport(Model model) {
        model.addAttribute("sales", saleService.getAllSales());
        model.addAttribute("totalSales", saleService.countSales());
        model.addAttribute("totalRevenue", saleService.getTotalRevenue() != null ? saleService.getTotalRevenue() : 0.0);
        return "director/report-sales";
    }

    @GetMapping("/reports/leases")
    public String leasesReport(Model model) {
        model.addAttribute("leases", leaseService.getAllLeases());
        model.addAttribute("totalLeases", leaseService.getAllLeases().size());
        model.addAttribute("activeLeases", leaseService.getActiveLeases().size());
        model.addAttribute("totalRevenue", leaseService.getTotalLeaseRevenue() != null ? leaseService.getTotalLeaseRevenue() : 0.0);
        return "director/report-leases";
    }

    @GetMapping("/reports/rents")
    public String rentsReport(Model model) {
        model.addAttribute("rents", rentService.getAllRents());
        model.addAttribute("totalRents", rentService.countRents());
        model.addAttribute("activeRents", rentService.countActiveRents());
        model.addAttribute("totalRevenue", rentService.getTotalRentRevenue() != null ? rentService.getTotalRentRevenue() : 0.0);
        return "director/report-rents";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "director/list-employees";
    }

    @GetMapping("/employees/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", Role.values());
        model.addAttribute("salons", salonService.getAllSalons());
        return "director/new-employee";
    }

    @PostMapping("/employees/new")
    public String createEmployee(@ModelAttribute("employee") Employee employee,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (employeeService.existsByLogin(employee.getLogin())) {
            model.addAttribute("errorMessage", "Pracownik z takim loginem już istnieje.");
            model.addAttribute("employee", employee);
            model.addAttribute("roles", Role.values());
            model.addAttribute("salons", salonService.getAllSalons());
            return "director/new-employee";
        }

        employee.setActive(true);
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Pracownik został dodany pomyślnie.");
        return "redirect:/director/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            model.addAttribute("errorMessage", "Nie znaleziono pracownika o podanym ID.");
            return "error";
        }

        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());
        model.addAttribute("salons", salonService.getAllSalons());
        return "director/update-employee";
    }

    @PutMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute("employee") Employee employee,
                                 RedirectAttributes redirectAttributes) {
        employeeService.updateEmployee(id, employee);
        redirectAttributes.addFlashAttribute("successMessage", "Dane pracownika zostały zaktualizowane.");
        return "redirect:/director/employees";
    }

    @GetMapping("/employees/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            model.addAttribute("errorMessage", "Nie znaleziono pracownika o podanym ID.");
            return "error";
        }

        model.addAttribute("employee", employee);
        return "director/employee-detail";
    }

    @DeleteMapping("/employees/deactivate/{id}")
    public String deactivateEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deactivateEmployee(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pracownik został dezaktywowany.");
        return "redirect:/director/employees";
    }
}
