package service;

import filter.AbstractFilter;
import domain.Car;
import repository.IRepository;
import validator.Validator;
import java.util.ArrayList;


public class FilteredCarsService extends CarsService {
    private AbstractFilter<Car> filter;
    public FilteredCarsService(IRepository <String, Car> repo, Validator<Car> validator,AbstractFilter<Car> filter) {
        super(repo, validator);
        this.filter = filter;
    }
    @Override
    public ArrayList<Car> getAll() {
        ArrayList<Car> cars = super.getAll();
        ArrayList<Car> filteredDCars = new ArrayList<>();
        for (Car car : cars) {
            if (filter.acceptEntity(car)) {
                filteredDCars.add(car);
            }
        }
        return filteredDCars;
    }

}
