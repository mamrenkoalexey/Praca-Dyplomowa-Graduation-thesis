package thesis.Graduation.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.service.CarService;

import java.util.List;

@Controller
public class MainController {

    private final CarService carService;

    public MainController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("brands", carService.getAllBrands());
        model.addAttribute("carModels", carService.getAllModels());
        model.addAttribute("bodyTypes", BodyType.values());
        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("statuses", CarStatus.values());
        model.addAttribute("years", carService.getAllYears());

        model.addAttribute("randomCars", carService.randomCars(6));
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String brand,
                         @RequestParam(required = false) String carModel,
                         @RequestParam(required = false) BodyType bodyType,
                         @RequestParam(required = false) Double priceFrom,
                         @RequestParam(required = false) Double priceTo,
                         @RequestParam(required = false) FuelType fuelType,
                         @RequestParam(required = false) Integer productionYear,
                         @RequestParam(required = false) Double mileageFrom,
                         @RequestParam(required = false) Double mileageTo,
                         @RequestParam(required = false) CarStatus status,
                         Model model) {

        boolean noCriteria = (brand == null || brand.isBlank())
                && (carModel == null || carModel.isBlank())
                && bodyType == null
                && priceFrom == null
                && priceTo == null
                && fuelType == null
                && productionYear == null
                && mileageFrom == null
                && mileageTo == null
                && status == null;

        model.addAttribute("noCriteria", noCriteria);

        List<Car> listOfSearchCars = carService.findSearchCars(
                brand, carModel, bodyType, priceFrom, priceTo, fuelType,
                productionYear, mileageFrom, mileageTo, status
        );

        model.addAttribute("listOfSearchCars", listOfSearchCars);

        model.addAttribute("brands", carService.getAllBrands());
        model.addAttribute("selBrand", brand);

        model.addAttribute("carModels", carService.getAllModels());
        model.addAttribute("selCarModel", carModel);

        model.addAttribute("bodyTypes", BodyType.values());
        model.addAttribute("selBodyType", bodyType);

        model.addAttribute("priceFrom", priceFrom);
        model.addAttribute("priceTo", priceTo);

        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("selFuelType", fuelType);

        model.addAttribute("statuses", CarStatus.values());
        model.addAttribute("selStatus", status);

        model.addAttribute("years", carService.getAllYears());
        model.addAttribute("selYear", productionYear);

        model.addAttribute("mileageFrom", mileageFrom);
        model.addAttribute("mileageTo", mileageTo);

        model.addAttribute("listOfCars", carService.randomCars(12));

        return "search";
    }
}
