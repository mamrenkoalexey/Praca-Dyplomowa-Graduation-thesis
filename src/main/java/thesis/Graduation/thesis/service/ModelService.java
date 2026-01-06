package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Model;
import thesis.Graduation.thesis.repository.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public boolean existsByNameAndBrand(String name, Long brandId) {
        return modelRepository.existsByNameIgnoreCaseAndBrandId(name, brandId);
    }

    public Model save(Model model) {
        if (existsByNameAndBrand(model.getName(), model.getBrand().getId())) {
            throw new IllegalArgumentException("Taki model już istnieje dla tej marki!");
        }
        return modelRepository.save(model);
    }

    public int count() {
        return (int) modelRepository.count();
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));
    }
}
