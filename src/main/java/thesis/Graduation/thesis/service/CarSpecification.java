package thesis.Graduation.thesis.service;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.entity.enums.CarStatus;

public class CarSpecification {

    public static Specification<Car> search(String brand,
                                            String carModel,
                                            BodyType bodyType,
                                            Double priceFrom,
                                            Double priceTo,
                                            FuelType fuelType,
                                            Integer year,
                                            Double mileageFrom,
                                            Double mileageTo,
                                            CarStatus status) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (brand != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("model").get("brand").get("name"), brand));
            }
            if (carModel != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("model").get("name"), carModel));
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
                predicate = cb.and(predicate, cb.equal(root.get("productionYear"), year));
            }
            if (mileageFrom != null) {
                predicate = cb.and(predicate, cb.ge(root.get("mileage"), mileageFrom));
            }
            if (mileageTo != null) {
                predicate = cb.and(predicate, cb.le(root.get("mileage"), mileageTo));
            }
            if (status != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }

            return predicate;
        };
    }

}
