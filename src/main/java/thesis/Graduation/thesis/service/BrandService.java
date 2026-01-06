package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Brand;
import thesis.Graduation.thesis.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public boolean existsByName(String name) {
        return brandRepository.existsByNameIgnoreCase(name);
    }
    public Brand save(Brand brand) {
        if (existsByName(brand.getName())) {
            throw new IllegalArgumentException("Taka marka już istnieje w bazie danych!");
        }
        return brandRepository.save(brand);
    }
    public int count() {
        return (int) brandRepository.count();
    }
}
