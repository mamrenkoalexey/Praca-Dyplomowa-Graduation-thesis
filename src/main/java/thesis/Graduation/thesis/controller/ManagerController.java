package thesis.Graduation.thesis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.service.CarService;
import thesis.Graduation.thesis.service.ModelService;
import thesis.Graduation.thesis.service.SalonService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final CarService carService;
    private final ModelService modelService;
    private final SalonService salonService;

    public ManagerController(CarService carService, ModelService modelService, SalonService salonService) {
        this.carService = carService;
        this.modelService = modelService;
        this.salonService = salonService;
    }

    // === Добавление новой машины ===
    @GetMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public String addCar(@RequestParam(value = "from", required = false) String returnUrl, Model model) {
        model.addAttribute("newCar", new Car());
        model.addAttribute("brands", carService.getAllBrands());
        model.addAttribute("models", carService.getAllModels());
        model.addAttribute("salons", salonService.getAllSalons());
        model.addAttribute("carFuelTypes", FuelType.values());
        model.addAttribute("carBodyTypes", BodyType.values());
        model.addAttribute("carYears", carService.getAllProductionYear());
        model.addAttribute("returnUrl", returnUrl != null ? returnUrl : "/");
        return "new-car";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public String saveCar(@ModelAttribute("newCar") Car car,
                          @RequestParam(value = "from", required = false) String returnUrl) {
        car.setStatus(CarStatus.AVAILABLE);
        carService.saveCar(car);
        return "redirect:" + (returnUrl != null ? returnUrl : "/");
    }

    // === Добавление новой модели ===
    @GetMapping("/add-model")
    @PreAuthorize("hasRole('MANAGER')")
    public String addModelForm(@RequestParam(value = "from", required = false) String from, Model model) {
        model.addAttribute("newModel", new thesis.Graduation.thesis.entity.Model());
        model.addAttribute("brands", carService.getAllBrands());
        model.addAttribute("from", from != null ? from : "/manager/add");
        return "new-model";
    }

    @PostMapping("/add-model")
    @PreAuthorize("hasRole('MANAGER')")
    public String addModel(@ModelAttribute("newModel") thesis.Graduation.thesis.entity.Model newModel,
                           @RequestParam(value = "from", required = false) String from) {
        modelService.save(newModel);
        return "redirect:" + (from != null ? from : "/manager/add");
    }
}
