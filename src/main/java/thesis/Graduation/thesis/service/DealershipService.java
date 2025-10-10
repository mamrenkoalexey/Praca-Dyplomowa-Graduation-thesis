package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Dealership;
import thesis.Graduation.thesis.repository.DealershipRepository;

import java.util.List;

@Service
public class DealershipService {

    private final DealershipRepository dealershipRepository;

    public DealershipService(DealershipRepository dealershipRepository) {
        this.dealershipRepository = dealershipRepository;
    }

    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }

    public Dealership getDealershipById(Long id) {
        return dealershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dealership not found"));
    }

    public Dealership saveDealership(Dealership dealership) {
        return dealershipRepository.save(dealership);
    }

    public void deleteDealership(Long id) {
        dealershipRepository.deleteById(id);
    }

    public List<Dealership> getDealershipsForCar(Long carId) {
        return dealershipRepository.findAllByCars_Id(carId);
    }
}