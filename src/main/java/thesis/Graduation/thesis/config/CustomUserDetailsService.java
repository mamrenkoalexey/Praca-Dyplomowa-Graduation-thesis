package thesis.Graduation.thesis.config; // Или ваш пакет для сервисов

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Client;
import thesis.Graduation.thesis.entity.Employee;
import thesis.Graduation.thesis.repository.ClientRepository;
import thesis.Graduation.thesis.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByLogin(username).orElse(null);
        if (employee != null) {
            return new CustomUserDetails(employee);
        }

        Client client = clientRepository.findByLogin(username).orElse(null);
        if (client != null) {
            return new CustomUserDetails(client);
        }

        throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie: " + username);
    }

}