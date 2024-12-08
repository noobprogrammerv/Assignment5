/*
package tests;

import domain.Car;
import repository.FakeRepo;
import repository.IRepository;
import service.CarsService;
import validator.CarValidator;
import validator.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarsServiceTestWithFakeRepo {
    private IRepository<String, Car> fakeRepo;
    private CarValidator carValidator;
    private CarsService carsService;

    @BeforeEach
    public void setUp_initializesRepoAndValidator_setsUpService() {
        fakeRepo = new FakeRepo();
        carValidator = new CarValidator();
        carsService = new CarsService(fakeRepo, carValidator);
    }

    @Test
    public void addCar_validCar_addsCarToRepo() {


        String id = "1";
        String make = "Renault";
        String model = "Clio";
        int year = 2007;
        carsService.addCar(id, make, model, year);

        Car addedCar = fakeRepo.findEntityById(id);
        assertEquals(id, addedCar.getId());
        assertEquals(make, addedCar.getMake());
        assertEquals(model, addedCar.getModel());
        assertEquals(year, addedCar.getYear());
    }

    @Test
    public void addCar_invalidYear_throwsValidateException() {
        String id = "2";
        String make = "Dacia";
        String model = "Duster";
        int year = -2019;

        try {
            carsService.addCar(id, make, model, year);
        } catch (ValidateException ex) {
            assertTrue(ex.getMessage().contains("Car year must be positive!"));
        }
    }


    @Test
    public void deleteCar_existingCar_removesCarFromRepo() {
        String id = "3";
        Car car = new Car(id, "VW", "Tiguan", 2012);
        fakeRepo.addEntity(id, car);
        carsService.deleteCar(id);

        assertNull(fakeRepo.findEntityById(id));
    }

    @Test
    public void findCar_existingCar_returnsCar() {
        String id = "4";
        Car car = new Car(id, "Hyundai", "Tucson", 2008);
        fakeRepo.addEntity(id, car);
        Car foundCar = carsService.findCarById(id);

        assertEquals(car, foundCar);
    }

    @Test
    public void getAllCars_multipleCars_returnsAllCars() {
        Car car1 = new Car("5", "BMW", "X5", 2017);
        Car car2 = new Car("6", "Audi", "A4", 2016);
        fakeRepo.addEntity(car1.getId(), car1);
        fakeRepo.addEntity(car2.getId(), car2);

        assertEquals(2, carsService.getAll().size());
    }

    @Test
    public void updateCar_existingCar_updatesCarInRepo() {

        carsService.addCar("1", "Dacia", "Logan", 2009);
        carsService.modifyCar("1", "Honda", "Civic", 2017);

        Car modifiedCar = carsService.findCarById("1");
        assertEquals("Honda", modifiedCar.getMake());
        assertEquals("Civic", modifiedCar.getModel());
        assertEquals(2017, modifiedCar.getYear());
    }

}

*/
