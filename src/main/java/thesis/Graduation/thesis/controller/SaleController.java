package thesis.Graduation.thesis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Client;
import thesis.Graduation.thesis.entity.Employee;
import thesis.Graduation.thesis.entity.Sale;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.service.CarService;
import thesis.Graduation.thesis.service.ClientService;
import thesis.Graduation.thesis.service.EmployeeService;
import thesis.Graduation.thesis.service.SaleService;

import java.util.List;

@Controller
@RequestMapping("/seller/sales")
@PreAuthorize("hasRole('SELLER')")
public class SaleController {

    private final SaleService saleService;
    private final CarService carService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    public SaleController(SaleService saleService, CarService carService,
                         ClientService clientService, EmployeeService employeeService) {
        this.saleService = saleService;
        this.carService = carService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listSales(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales);
        model.addAttribute("totalSales", sales.size());
        model.addAttribute("totalRevenue", saleService.getTotalRevenue());
        return "seller/sales/list-sales";
    }

    @GetMapping("/new")
    public String newSaleForm(Model model) {
        // Get only available cars
        List<Car> availableCars = carService.getAllCars().stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();

        model.addAttribute("cars", availableCars);
        model.addAttribute("clients", clientService.getActiveClients());
        model.addAttribute("employees", employeeService.getActiveEmployees());
        model.addAttribute("paymentMethods", PaymentMethod.values());

        return "seller/sales/new-sale";
    }

    @PostMapping("/new")
    public String createSale(
            @RequestParam Long carId,
            @RequestParam Long clientId,
            @RequestParam Long employeeId,
            @RequestParam PaymentMethod paymentMethod,
            @RequestParam(required = false) String notes,
            RedirectAttributes redirectAttributes) {

        try {
            Sale sale = saleService.createSale(carId, clientId, employeeId, paymentMethod, notes);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Sprzedaż została pomyślnie zarejestrowana! ID: " + sale.getId());
            return "redirect:/seller/sales/" + sale.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas tworzenia sprzedaży: " + e.getMessage());
            return "redirect:/seller/sales/new";
        }
    }

    @GetMapping("/{id}")
    public String viewSale(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSaleById(id);
        if (sale == null) {
            model.addAttribute("errorMessage", "Nie znaleziono sprzedaży o podanym ID.");
            return "error";
        }
        model.addAttribute("sale", sale);
        return "seller/sales/sale-detail";
    }

    @DeleteMapping("/{id}")
    public String deleteSale(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            saleService.deleteSale(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Sprzedaż została pomyślnie usunięta.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Błąd podczas usuwania sprzedaży: " + e.getMessage());
        }
        return "redirect:/seller/sales";
    }

    @GetMapping("/employee/{employeeId}")
    public String viewEmployeeSales(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            model.addAttribute("errorMessage", "Nie znaleziono pracownika.");
            return "error";
        }

        List<Sale> sales = saleService.getSalesByEmployee(employeeId);
        model.addAttribute("sales", sales);
        model.addAttribute("employee", employee);
        model.addAttribute("totalSales", sales.size());
        model.addAttribute("totalRevenue", sales.stream()
                .mapToDouble(Sale::getTotalAmount).sum());

        return "seller/sales/employee-sales";
    }

    @GetMapping("/client/{clientId}")
    public String viewClientSales(@PathVariable Long clientId, Model model) {
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            model.addAttribute("errorMessage", "Nie znaleziono klienta.");
            return "error";
        }

        List<Sale> sales = saleService.getSalesByClient(clientId);
        model.addAttribute("sales", sales);
        model.addAttribute("client", client);
        model.addAttribute("totalSales", sales.size());
        model.addAttribute("totalRevenue", sales.stream()
                .mapToDouble(Sale::getTotalAmount).sum());

        return "seller/sales/client-sales";
    }
}
