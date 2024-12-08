package main;

import repository.*;
import domain.Car;
import domain.Reservation;
import service.CarsService;
import service.ReservationsService;
import ui.UI;
import validator.CarValidator;
import validator.ReservationValidator;
import repository.IRepository;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class Main {
    public static void printCars (IRepository <String, Car> repo) {
        Iterator<Car> Iterator = repo.getAllEntities().iterator();
        while(Iterator.hasNext()) {
            System.out.println(Iterator.next());
        }
    }
    public static void printReservations (IRepository <String, Reservation> repo) {
        Iterator<Reservation> Iterator = repo.getAllEntities().iterator();
        while(Iterator.hasNext()) {
            System.out.println(Iterator.next());
        }
    }
    public static void main(String[] args) {




        ///lab3cod///
        //CarRepository carRepo = new CarRepository();
        //CarsService carsService = new CarsService(carRepo);
        /*ReservationRepository reservationRepo = new ReservationRepository();
        ReservationsService reservationsService = new ReservationsService(reservationRepo);*/
        //UI ui = new UI(carsService, reservationsService);
        //ui.Menu();



        Properties prop = new Properties();
        IRepository<String, Car> carRepo = null;
        IRepository<String, Reservation> reservationRepo = null;

        try {
            prop.load(new FileReader("settings.properties"));

            String carRepoType = prop.getProperty("repo_type_cars");
            String carRepoPath = prop.getProperty("repo_path_cars");
            String reservationRepoType = prop.getProperty("repo_type_reservations");
            String reservationRepoPath = prop.getProperty("repo_path_reservations");

            if (carRepoType.equals("text"))
                carRepo = new TextFileCarsRepository(carRepoPath);
            else if (carRepoType.equals("binary"))
                carRepo = new BinaryFileCarsRepository(carRepoPath);
            else if (carRepoType.equals("DB")) {
                carRepo = new DBCarsRepository();
            }/* else if (carRepoType.equals("JSON")) {
                carRepo = new JSONFileCarsRepository(carRepoPath);
                ((JSONFileCarsRepository) carRepo).readFromFile();
            } else if (carRepoType.equals("XML")) {
                carRepo = new XMLFileCarsRepository(carRepoPath);
                ((XMLFileCarsRepository) carRepo).readFromFile();
            }*/

            if (reservationRepoType.equals("text"))
                reservationRepo = new TextFileReservationsRepository(reservationRepoPath);
            else if (reservationRepoType.equals("binary"))
                reservationRepo = new BinaryFileReservationsRepository(reservationRepoPath);
            else if (reservationRepoType.equals("DB")) {
                reservationRepo = new DBReservationsRepository();
            }

            CarValidator carValidator = new CarValidator();
            ReservationValidator reservationValidator = new ReservationValidator();

            ReservationsService reservationsService = new ReservationsService(reservationRepo, reservationValidator);
            CarsService carsService = new CarsService(carRepo, carValidator);
            UI ui = new UI(carsService, reservationsService);
            ui.run();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ///lab3///




                    ///TEST FILTERED CARS SERVICE///
        /*Car c1 = new Car("1", "Dacia", "Logan", 1992);
        Car c2 = new Car("2", "Renault", "Scenic", 1991);
        Car c3 = new Car("3", "Hyundai", "Tucson", 1987);
        Car c4 = new Car("4", "Dacia", "Duster", 2019);
        Car c5 = new Car("5", "Mercedes", "GLS", 2021);
        CarRepository repo = new CarRepository();
        repo.addEntity(c1.getId(), c1);
        repo.addEntity(c2.getId(), c2);
        repo.addEntity(c3.getId(), c3);
        FilterCarsByYear filter = new FilterCarsByYear(1991);
        FilteredCarsService filterCars = new FilteredCarsService(repo, filter);
        for (Car car: filterCars.getAll()) {
            System.out.println(car);
        }*/

                    ///TEST FILTERED CARS REPO///
        /*Car c1 = new Car("1", "Dacia", "Logan", 1992);
        Car c2 = new Car("2", "Renault", "Scenic", 1991);
        Car c3 = new Car("3", "Hyundai", "Tucson", 1987);
        Car c4 = new Car("4", "Dacia", "Duster", 2019);
        Car c5 = new Car("5", "Mercedes", "GLS", 2021);

        FilteredRepository filteredRepository = new FilteredRepository(new FilterCarsByYear(1992));
        filteredRepository.addEntity(c1.getId(), c1);
        filteredRepository.addEntity(c2.getId(), c2);
        filteredRepository.addEntity(c3.getId(), c3);
        filteredRepository.addEntity(c4.getId(), c4);
        printCars(filteredRepository);*/
                    ///TEST FILTERED RESERVATIONS REPO///
        /*Reservation r1 = new Reservation("1", LocalDate.of(1992,1,2), "3");
        Reservation r2 = new Reservation("2", LocalDate.of(1992,1,3), "4");
        Reservation r3 = new Reservation("3", LocalDate.of(1992,1,2), "5");
        Reservation r4 = new Reservation("4", LocalDate.of(1992,1,18), "6");
        Reservation r5 = new Reservation("5", LocalDate.of(1992,1,23), "7");

        FilteredRepository filteredRepository =
                new FilteredRepository(new FilterReservationsByDate(LocalDate.of(1992,1,2)));
        filteredRepository.addEntity(r1.getId(), r1);
        filteredRepository.addEntity(r2.getId(), r2);
        filteredRepository.addEntity(r3.getId(), r3);
        filteredRepository.addEntity(r4.getId(), r4);
        printReservations(filteredRepository);*/

    }
}
