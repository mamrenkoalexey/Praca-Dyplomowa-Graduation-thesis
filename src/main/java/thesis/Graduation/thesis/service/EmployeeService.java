package thesis.Graduation.thesis.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Employee;
import thesis.Graduation.thesis.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getActiveEmployees() {
        return employeeRepository.findAll().stream()
                .filter(Employee::isActive)
                .toList();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee getEmployeeByLogin(String login) {
        return employeeRepository.findByLogin(login).orElse(null);
    }

    public long countEmployees() {
        return employeeRepository.count();
    }

    public long countActiveEmployees() {
        return employeeRepository.findAll().stream()
                .filter(Employee::isActive)
                .count();
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhone(updatedEmployee.getPhone());
        existing.setRole(updatedEmployee.getRole());
        existing.setSalon(updatedEmployee.getSalon());
        existing.setSalary(updatedEmployee.getSalary());
        existing.setActive(updatedEmployee.getActive());

        if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));
        }

        return employeeRepository.save(existing);
    }

    public void deactivateEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setActive(false);
            employeeRepository.save(employee);
        }
    }

    public boolean existsByLogin(String login) {
        return employeeRepository.findByLogin(login).isPresent();
    }
}
