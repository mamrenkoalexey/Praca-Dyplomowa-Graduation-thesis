package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Brand;
import thesis.Graduation.thesis.repository.BrandRepository;

import java.util.List;

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
        if (brand.getId() == null && existsByName(brand.getName())) {
            throw new IllegalArgumentException("Taka marka już istnieje w bazie danych!");
        }
        return brandRepository.save(brand);
    }
    
    public int count() {
        return (int) brandRepository.count();
    }
    
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
    
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }
}
