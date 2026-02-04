package thesis.Graduation.thesis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thesis.Graduation.thesis.entity.Brand;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.service.*;

@Controller
@RequestMapping("/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    private final CarService carService;
    private final ModelService modelService;
    private final SalonService salonService;
    private final BrandService brandService;
    private final ImageStorageService imageStorageService;
    private final SaleService saleService;
    private final ClientService clientService;

    public ManagerController(CarService carService, ModelService modelService, SalonService salonService, BrandService brandService, ImageStorageService imageStorageService, SaleService saleService, ClientService clientService) {
        this.carService = carService;
        this.modelService = modelService;
        this.salonService = salonService;
        this.brandService = brandService;
        this.imageStorageService = imageStorageService;
        this.saleService = saleService;
        this.clientService = clientService;
    }

    @GetMapping("/car-stats")
    public String manageCars(Model model) {
        long totalBrands = brandService.count();
        long totalModels = modelService.count();
        long totalCars = carService.count();
        long totalSales = saleService.countSales();
        long totalClients = clientService.countClients();
        Double totalRevenue = saleService.getTotalRevenue();

        model.addAttribute("totalBrands", totalBrands);
        model.addAttribute("totalModels", totalModels);
        model.addAttribute("totalCars", totalCars);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalRevenue", totalRevenue != null ? totalRevenue : 0.0);

        return "manager/stats-car";
    }


    @GetMapping("/add-car")
    public String addCar(Model model) {
        model.addAttribute("newCar", new Car());
        model.addAttribute("brands", carService.getAllBrands());
        model.addAttribute("models", carService.getAllModels());
        model.addAttribute("salons", salonService.getAllSalons());
        model.addAttribute("carFuelTypes", FuelType.values());
        model.addAttribute("carBodyTypes", BodyType.values());
        model.addAttribute("carYears", carService.getAllProductionYear());

        return "manager/new-car";
    }

    @PostMapping("/add-car")
    public String saveCar(@ModelAttribute("newCar") Car car) {
        car.setStatus(CarStatus.AVAILABLE);
        carService.saveCar(car);
        return "redirect:/manager/car-stats";
    }

    @GetMapping("/add-model")
    public String addModelForm(Model model) {
        model.addAttribute("newModel", new thesis.Graduation.thesis.entity.Model());
        model.addAttribute("brands", carService.getAllBrands());
        return "manager/new-model";
    }

    @PostMapping("/add-model")
    public String addModel(@ModelAttribute("newModel") thesis.Graduation.thesis.entity.Model newModel, Model model) {
        try {
            modelService.save(newModel);
            return "redirect:/manager/car-stats";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("newModel", new thesis.Graduation.thesis.entity.Model());
            model.addAttribute("brands", carService.getAllBrands());
            return "manager/new-model";
        }
    }

    @GetMapping("/add-brand")
    public String addBrandForm(Model model) {
        model.addAttribute("newBrand", new Brand());
        return "manager/new-brand";
    }

    @PostMapping("/add-brand")
    public String addBrand(@ModelAttribute("newBrand") Brand newBrand, Model model) {
        try {
            brandService.save(newBrand);
            return "redirect:/manager/car-stats";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("newBrand", new Brand());
            return "manager/new-brand";
        }
    }

    @GetMapping("/list-cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "manager/list-cars";
    }

    @GetMapping("/list-models")
    public String listModels(Model model) {
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("brands", carService.getAllBrands());
        return "manager/list-models";
    }

    @GetMapping("/list-brands")
    public String listBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "manager/list-brands";
    }

    @GetMapping("/update/car/{id}")
    public String updateCar(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        if (car == null) {
            model.addAttribute("errorMessage", "Nie znaleziono samochodu o podanym ID.");
            return "error";
        }
        model.addAttribute("car", car);
        model.addAttribute("models", carService.getAllModels());
        model.addAttribute("carBodyTypes", BodyType.values());
        model.addAttribute("carFuelTypes", FuelType.values());
        model.addAttribute("carYears", carService.getAllProductionYear());
        model.addAttribute("salons", salonService.getAllSalons());
        model.addAttribute("statuses", CarStatus.values());

        return "manager/update-car";
    }

    @PutMapping("/update/car/{id}")
    public String saveUpdatedCar(@PathVariable Long id, @ModelAttribute("car") Car updatedCar,
                                 @RequestParam(value = "image", required = false) MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            imageStorageService.deleteImage(updatedCar.getCarImage());

            String newFileName = imageStorageService.saveImage(image);
            updatedCar.setCarImage(newFileName);
        }

        carService.updateCar(id, updatedCar);
        return "redirect:/manager/list-cars";
    }

    @GetMapping("/update/model/{id}")
    public String updateModel(@PathVariable Long id, Model model) {
        thesis.Graduation.thesis.entity.Model carModel = modelService.getModelById(id);
        if (carModel == null) {
            model.addAttribute("errorMessage", "Nie znaleziono modelu o podanym ID.");
            return "error";
        }
        model.addAttribute("carModel", carModel);
        model.addAttribute("brands", carService.getAllBrands());

        return "manager/update-model";
    }

    @PutMapping("/update/model/{id}")
    public String saveUpdateModel(@PathVariable Long id, @ModelAttribute("carModel") thesis.Graduation.thesis.entity.Model carModel) {
        modelService.save(carModel);
        return "redirect:/manager/list-models";
    }

    @GetMapping("/update/brand/{id}")
    public String updateBrand(@PathVariable Long id, Model model) {
        Brand brand = brandService.getBrandById(id);
        if (brand == null) {
            model.addAttribute("errorMessage", "Nie znaleziono marki o podanym ID.");
            return "error";
        }
        model.addAttribute("brand", brand);

        return "manager/update-brand";
    }

    @PutMapping("/update/brand/{id}")
    public String saveUpdateBrand(@PathVariable Long id, @ModelAttribute("brand") Brand brand, Model model) {
        try {
            brandService.save(brand);
            return "redirect:/manager/list-brands";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("brand", brand);
            return "manager/update-brand";
        }
    }

}
