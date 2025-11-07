package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Model;
import thesis.Graduation.thesis.repository.ModelRepository;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
