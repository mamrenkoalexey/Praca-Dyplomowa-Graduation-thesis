package thesis.Graduation.thesis.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thesis.Graduation.thesis.config.CustomUserDetails;
import thesis.Graduation.thesis.entity.Client;
import thesis.Graduation.thesis.entity.Employee;
import thesis.Graduation.thesis.repository.ClientRepository;
import thesis.Graduation.thesis.repository.EmployeeRepository;

@Controller
public class ProfileController {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public ProfileController(EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Long userId = userDetails.getId();

        if (userDetails.isEmployee()) {
            Employee employee = employeeRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            model.addAttribute("employee", employee);
            model.addAttribute("userType", "employee");

            // Count transactions (safely, without triggering lazy loading)
            int totalSales = 0;
            int totalRents = 0;
            int totalLeases = 0;

            try {
                totalSales = employee.getSales() != null ? employee.getSales().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            try {
                totalRents = employee.getRents() != null ? employee.getRents().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            try {
                totalLeases = employee.getLeases() != null ? employee.getLeases().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            model.addAttribute("totalSales", totalSales);
            model.addAttribute("totalRents", totalRents);
            model.addAttribute("totalLeases", totalLeases);
            model.addAttribute("totalTransactions", totalSales + totalRents + totalLeases);

        } else {
            Client client = clientRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            model.addAttribute("client", client);
            model.addAttribute("userType", "client");

            // Count transactions (safely, without triggering lazy loading)
            int totalSales = 0;
            int totalRents = 0;
            int totalLeases = 0;

            try {
                totalSales = client.getSales() != null ? client.getSales().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            try {
                totalRents = client.getRents() != null ? client.getRents().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            try {
                totalLeases = client.getLeases() != null ? client.getLeases().size() : 0;
            } catch (Exception e) {
                // Ignore if lazy loading fails
            }

            model.addAttribute("totalSales", totalSales);
            model.addAttribute("totalRents", totalRents);
            model.addAttribute("totalLeases", totalLeases);
            model.addAttribute("totalTransactions", totalSales + totalRents + totalLeases);
        }

        return "profile";
    }
}
