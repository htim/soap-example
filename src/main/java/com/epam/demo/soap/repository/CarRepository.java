package com.epam.demo.soap.repository;

import com.epam.demo.soap.domain.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Timur_Khudiakov on 9/5/2017.
 */
@Repository
public class CarRepository {

    private List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("WV", "Polo", "White"));
        cars.add(new Car("Audi", "A4", "Black"));
        cars.add(new Car("WV", "Jetta", "White"));
        cars.add(new Car("WV", "Passat", "White"));
    }

    public List<Car> getCarsByBrand(String brand) {
        return cars.stream().filter(x -> x.getBrand().equals(brand)).collect(Collectors.toList());
    }

}
