package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Client;
import thesis.Graduation.thesis.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getActiveClients() {
        return clientRepository.findAll().stream()
                .filter(Client::getActive)
                .toList();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public long countClients() {
        return clientRepository.count();
    }
}
