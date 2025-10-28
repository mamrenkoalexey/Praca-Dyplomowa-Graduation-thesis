//package thesis.Graduation.thesis.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import thesis.Graduation.thesis.entity.Car;
//import thesis.Graduation.thesis.entity.Salon;
//import thesis.Graduation.thesis.service.CarService;
//import thesis.Graduation.thesis.service.DealershipService;
//
//import java.util.List;
//
//@Controller
//public class CarController {
//
//    private final CarService carService;
//    private final DealershipService dealershipService;
//
//    public CarController(CarService carService, DealershipService dealershipService) {
//        this.carService = carService;
//        this.dealershipService = dealershipService;
//    }
//
//
//    @GetMapping("/car/{id}")
//    public String carDetails(@PathVariable Long id, @RequestParam(required = false, name = "from") String returnUrl, Model model) {
//        Car car = carService.getCarById(id);
//        if (car == null) {
//            model.addAttribute("errorMessage", "Nie znaleziono samochodu o podanym ID.");
//            return "error";
//        }
//        model.addAttribute("car", car);
//        model.addAttribute("listOfCars", carService.randomCars(12, id));
//        System.out.println("Return URL: " + returnUrl);
//
//        model.addAttribute("returnUrl", returnUrl != null ? returnUrl : "/");
//        return "car-detail";
//    }
//
//    @GetMapping("/car/{id}/dealerships")
//    public String carDetailsDealerships(@PathVariable Long id, @RequestParam(required = false, name = "from") String returnUrl, Model model) {
//        Car car = carService.getCarById(id);
//        if (car == null) {
//            model.addAttribute("errorMessage", "Nie znaleziono samochodu o podanym ID.");
//            return "error";
//        }
//        model.addAttribute("car", car);
//
//        List<Salon> salons = dealershipService.getDealershipsForCar(id);
//
//        model.addAttribute("dealers", salons);
//        model.addAttribute("returnUrl", returnUrl != null ? returnUrl : "/");
//        model.addAttribute("listOfCars", carService.randomCars(12, id));
//        return "car-dealership";
//    }
//
//}
