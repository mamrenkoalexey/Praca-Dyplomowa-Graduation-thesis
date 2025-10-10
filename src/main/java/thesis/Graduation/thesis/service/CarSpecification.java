package thesis.Graduation.thesis.service;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.FuelType;

public class CarSpecification {

    public static Specification<Car> search(String brand,
                                            String carModel,
                                            BodyType bodyType,
                                            Double priceFrom,
                                            Double priceTo,
                                            FuelType fuelType,
                                            Integer year,
                                            Integer mileageFrom,
                                            Integer mileageTo) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (brand != null) {
                predicate = cb.and(predicate, cb.equal(root.get("brand"), brand));
            }
            if (carModel != null) {
                predicate = cb.and(predicate, cb.equal(root.get("model"), carModel));
            }
            if (bodyType != null) {
                predicate = cb.and(predicate, cb.equal(root.get("bodyType"), bodyType));
            }
            if (fuelType != null) {
                predicate = cb.and(predicate, cb.equal(root.get("fuelType"), fuelType));
            }
            if (priceFrom != null) {
                predicate = cb.and(predicate, cb.ge(root.get("price"), priceFrom));
            }
            if (priceTo != null) {
                predicate = cb.and(predicate, cb.le(root.get("price"), priceTo));
            }
            if (year != null) {
                predicate = cb.and(predicate, cb.ge(root.get("year"), year));
            }
            if (mileageFrom != null) {
                predicate = cb.and(predicate, cb.ge(root.get("mileage"), mileageFrom));
            }
            if (mileageTo != null) {
                predicate = cb.and(predicate, cb.le(root.get("mileage"), mileageTo));
            }
            return predicate;
        };

    }

}
