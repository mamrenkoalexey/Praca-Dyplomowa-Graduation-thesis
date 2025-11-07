package thesis.Graduation.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import thesis.Graduation.thesis.entity.Brand;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.repository.BrandRepository;
import thesis.Graduation.thesis.repository.ModelRepository;
import thesis.Graduation.thesis.service.CarService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    public MainController(CarService carService, ModelRepository modelRepository, BrandRepository brandRepository) {
        this.carService = carService;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private final CarService carService;
    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;


    @GetMapping("/models")
    @ResponseBody
    public List<Map<String, Object>> getModelsByBrand(@RequestParam Long brand) {
        return carService.getModelsByBrand(brand).stream()
                .map(m -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", m.getId());
                    map.put("name", m.getName());
                    return map;
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("carBrands", carService.getAllBrands());
        model.addAttribute("carModels", carService.getAllModels());
        model.addAttribute("carBodyTypes", BodyType.values());
        model.addAttribute("carFuelTypes", FuelType.values());
        model.addAttribute("carYears", carService.getAllProductionYear());
        model.addAttribute("searchCarBrand", null);
        model.addAttribute("searchCarModel", null);
        model.addAttribute("randomCars", carService.randomCars(6));
        return "index";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, name = "carBrand") Long brandId,
            @RequestParam(required = false, name = "carModel") Long carModelId,
            @RequestParam(required = false, name = "carBodyType") BodyType bodyType,
            @RequestParam(required = false, name = "carPriceFrom") Double priceFrom,
            @RequestParam(required = false, name = "carPriceTo") Double priceTo,
            @RequestParam(required = false, name = "carFuelType") FuelType fuelType,
            @RequestParam(required = false, name = "carYear") Integer productionYear,
            @RequestParam(required = false, name = "carMileageFrom") Double mileageFrom,
            @RequestParam(required = false, name = "carMileageTo") Double mileageTo,
            Model model) {

        Brand brand = (brandId != null) ? brandRepository.findById(brandId).orElse(null) : null;
        thesis.Graduation.thesis.entity.Model carModel =
                (carModelId != null) ? modelRepository.findById(carModelId).orElse(null) : null;

        model.addAttribute("noCriteria", carService.searchNullParam(brand, carModel,
                bodyType, priceFrom, priceTo, fuelType, productionYear, mileageFrom, mileageTo));

        List<Car> listOfSearchCars = carService.findSearchCars(
                brand, carModel, bodyType, priceFrom, priceTo, fuelType,
                productionYear, mileageFrom, mileageTo);

        model.addAttribute("listOfSearchCars", listOfSearchCars);

        model.addAttribute("carBrands", carService.getAllBrands());
        model.addAttribute("searchCarBrand", brand);

        if (brand != null) {
            model.addAttribute("carModels", carService.getModelsByBrand(brand.getId()));
        } else {
            model.addAttribute("carModels", Collections.emptyList());
        }
        model.addAttribute("searchCarModel", carModel);

        model.addAttribute("carBodyTypes", BodyType.values());
        model.addAttribute("searchCarBodyType", bodyType);

        model.addAttribute("carPriceFrom", priceFrom);
        model.addAttribute("carPriceTo", priceTo);

        model.addAttribute("carFuelTypes", FuelType.values());
        model.addAttribute("searchCarFuelType", fuelType);

        model.addAttribute("carYears", carService.getAllProductionYear());
        model.addAttribute("searchCarYear", productionYear);

        model.addAttribute("carMileageFrom", mileageFrom);
        model.addAttribute("carMileageTo", mileageTo);

        model.addAttribute("randomCars", carService.randomCars(12));

        return "search";
    }
}
