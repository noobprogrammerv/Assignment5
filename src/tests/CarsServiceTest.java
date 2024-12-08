/*
package tests;

import repository.CarRepository;
import repository.IRepository;
import service.CarsService;
import validator.Validator;
import org.junit.jupiter.api.Test;
import domain.Car;
import validator.CarValidator;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarsServiceTest {
    private int getItSize(Iterable<Car> cars) {
        Iterator<Car> it = cars.iterator();
        int numberOfCars = 0;
        while (it.hasNext()) {
            ++numberOfCars;
            it.next();
        }
        return numberOfCars;
    }
    @Test//addCar_addsCar
    public void addCar_addsCarIntoRepo_repoContainsAddedCar() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        assertEquals(0, getItSize(repo.getAllEntities()));
        service.addCar("1", "a","b",2000);
        assertEquals(1, getItSize(repo.getAllEntities()));
        assertEquals("a", service.findCarById("1").getMake());
    }

    @Test
    public void deleteCar_removesCarFromRepo_repoDoesNotContainDeletedCar() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        service.addCar("1", "Toyota", "Supra", 2015);
        assertEquals(1, getItSize(repo.getAllEntities()));
        service.deleteCar("1");
        assertEquals(0, getItSize(repo.getAllEntities()));
    }

    @Test
    public void findCarById_givenExistingId_returnsCorrectCar() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        service.addCar("1", "Dacia", "Logan", 2015);
        Car car = service.findCarById("1");
        assertEquals("Dacia", car.getMake());
        assertEquals("Logan", car.getModel());
        assertEquals(2015, car.getYear());
    }

    @Test
    public void updateCar_modifiesExistingCar_repoContainsUpdatedCar() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        service.addCar("1", "Dacia", "Logan", 2015);
        service.modifyCar("1", "Honda", "Civic", 2017);

        Car modifiedCar = service.findCarById("1");
        assertEquals("Honda", modifiedCar.getMake());
        assertEquals("Civic", modifiedCar.getModel());
        assertEquals(2017, modifiedCar.getYear());
    }

    @Test
    public void getAllCars_containsMultipleCars_returnsAllCars() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        service.addCar("1", "Toyota", "Camry", 2015);
        service.addCar("2", "Honda", "Civic", 2020);
        service.addCar("3", "Ford", "Focus", 2018);
        assertEquals(3, service.getAll().size());
    }

    @Test
    public void getAll_whenRepoIsEmpty_returnsEmptyList() {
        IRepository<String, Car> repo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);

        assertEquals(0, service.getAll().size());
    }

}
*/
