package service;

import domain.Car;
import repository.IRepository;
import validator.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import validator.ValidateException;

public class CarsService {
    private final IRepository<String, Car> carsRepo;
    private final Validator<Car> carValidator;

    public CarsService(IRepository<String, Car> repo, Validator<Car> validator) {
        this.carsRepo = repo;
        this.carValidator = validator;
    }

    /// COPY CONSTRUCTOR FOR GUI
    public CarsService(CarsService other) {
        this.carsRepo = other.carsRepo;
        this.carValidator = other.carValidator;
    }

    public void addCar(String id, String make, String model, int year) throws ValidateException {
        Car car = new Car(id, make, model, year);
        // We perform the validation
        carValidator.validate(car);
        carsRepo.addEntity(car.getId(), car);
    }
    public void deleteCar(String id) {
        carsRepo.deleteEntity(id);
    }
    public Car findCarById(String id) {
        return carsRepo.findEntityById(id);
    }
    public void modifyCar(String id, String make, String model, int year) throws ValidateException {
        Car car = new Car(id, make, model, year);
        carValidator.validate(car);
        carsRepo.modifyEntity(car.getId(), car);
    }

    public ArrayList<Car> getAll() {
        ArrayList<Car> cars = new ArrayList<>();
        for (Car car : carsRepo.getAllEntities()) {
            cars.add(car);
        }
        return cars;
    }
    // 4) Get all cars with a certain make
    public List<String> getAllCarsWithSameMake(String make) {
        return StreamSupport.stream(carsRepo.getAllEntities().spliterator(), false)
                .filter(reservation -> reservation.getMake().equals(make))
                .map(r -> r.getId()).distinct()
                .collect(Collectors.toList());
    }
    //FOR GUI
    public List<Car> getAllCarsWithSameMakeGUI(String make) {
        List<Car> carsWithSameMake = new ArrayList<>();
        for (Car car : carsRepo.getAllEntities()) {
            if (car.getMake().equalsIgnoreCase(make)) {
                carsWithSameMake.add(car);
            }
        }
        return carsWithSameMake;
    }


    // 5) Get all cars with same fabrication year
    public List<String> getAllCarsWithSameYear(int year) {
        return StreamSupport.stream(carsRepo.getAllEntities().spliterator(), false)
                .filter(reservation -> reservation.getYear() == year)
                .map(r -> r.getId()).distinct()
                .collect(Collectors.toList());
    }
    // FOR GUI
    public List<Car> getAllCarsWithSameYearGUI(int year) {
        List<Car> carsWithSameYear = new ArrayList<>();
        for (Car car : carsRepo.getAllEntities()) {
            if (car.getYear() == year) {
                carsWithSameYear.add(car);
            }
        }
        return carsWithSameYear;
    }
    ///  for bonus
    public IRepository<String, Car> getRepo() {
        return carsRepo;
    }

}
