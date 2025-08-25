package thesis.Graduation.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.EngineType;
import thesis.Graduation.thesis.service.CarService;


import java.util.Collections;
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
        model.addAttribute("bodyTypes", carService.getAllBodyTypes());
        model.addAttribute("engineTypes", carService.findAllEngineTypes());
        model.addAttribute("years", carService.getAllYears());

        List<Car> listOfCars = carService.getAllCars();

        Collections.shuffle(listOfCars);

        List<Car> randomCars = listOfCars.stream().limit(6).toList();
        model.addAttribute("randomCars", randomCars);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String brand,
                         @RequestParam(required = false) String carModel,
                         @RequestParam(required = false) BodyType bodyType,
                         @RequestParam(required = false) Double price,
                         @RequestParam(required = false) EngineType engineType,
                         @RequestParam(required = false) Integer year,
                         Model model) {

        List<Car> listOfCars = carService.findSearchCars(brand, carModel, bodyType, price, engineType, year);

        model.addAttribute("listOfSearchCars", listOfCars);


        return "search";
    }


}
