package ui;

import filter.AbstractFilter;
import filter.FilterCarsByYear;
import domain.Car;
import repository.CarRepository;
import repository.IRepository;
import service.CarsService;
import service.ReservationsService;
import service.FilteredCarsService;
import validator.CarValidator;
import validator.ValidateException;
//import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import validator.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UI {
    public UI(CarsService car, ReservationsService reservation) {
        carsService = car;
        reservationsService = reservation;
    }

    public static int ADDCAR = 1;
    public static int REMOVECAR = 2;
    public static int UPDATECAR = 3;
    public static int SHOWCARBYID = 4;
    public static int SHOWALLCARS = 5;
    public static int FILTERCARSBYYEAR = 6;
    public static int ADDRESERVATION = 7;
    public static int REMOVERESERVATION = 8;
    public static int UPDATERESERVATION = 9;
    public static int SHOWRESERVATIONBYID = 10;
    public static int SHOWALLRESERVATIONS = 11;
    public static int GETALLCARSWITHSAMEMAKE = 12;
    public static int GETALLCARSWITHSAMEYEAR = 13;
    public static int GETALLCARSRESERVEDAT = 14;
    public static int GETALLCARSRESERVEDBEFORE = 15;
    public static int GETALLCARSRESERVEDAFTER = 16;
    public static int SHOWMENU = 17;
    public static int EXIT = 0;

    public void showMenu() {
        System.out.println("-MENU-");
        System.out.println(ADDCAR + " - to add a car");
        System.out.println(REMOVECAR + " - to remove a car");
        System.out.println(UPDATECAR + " - to update a car");
        System.out.println(SHOWCARBYID + " - to show a car");
        System.out.println(SHOWALLCARS + " - to show all cars");
        System.out.println(FILTERCARSBYYEAR + " - to filter cars by year");
        System.out.println(ADDRESERVATION + " - to add a reservation");
        System.out.println(REMOVERESERVATION + " - to remove a reservation");
        System.out.println(UPDATERESERVATION + " - to update a reservation");
        System.out.println(SHOWRESERVATIONBYID + " - to show a reservation");
        System.out.println(SHOWALLRESERVATIONS + " - to show all reservations");
        System.out.println(GETALLCARSWITHSAMEMAKE + " - to show all cars with a certain make");
        System.out.println(GETALLCARSWITHSAMEYEAR + " - to show all cars with a certain year");
        System.out.println(GETALLCARSRESERVEDAT + " - to show all cars reserved at a certain date");
        System.out.println(GETALLCARSRESERVEDBEFORE + " - to show all cars reserved before a certain date");
        System.out.println(GETALLCARSRESERVEDAFTER + " - to show all cars reserved after a certain date");
        System.out.println(SHOWMENU + " - to show the menu");
        System.out.println(EXIT + " - to exit the program");
        System.out.print("Choose a command: ");
    }

    Car c1 = new Car("1", "Dacia", "Logan", 1992);
    Car c2 = new Car("2", "Renault", "Scenic", 1991);
    Car c3 = new Car("3", "Hyundai", "Tucson", 1987);
    Car c4 = new Car("4", "Dacia", "Duster", 2019);
    Car c5 = new Car("5", "Mercedes", "GLS", 2021);
    private final CarsService carsService;
    private final ReservationsService reservationsService;
    //private FilteredCarsService filteredCarsService;///sterge sau nu


    int command = SHOWMENU;
    int COUNTER = 0;
    Scanner scanner = new Scanner(System.in);

    public void run() {
        //daca e text, inseamna ca carsService nu e gol
        /*if (carsService.getAll().isEmpty()) {
            carsService.addCar("1", "Dacia", "Logan", 1992);
            carsService.addCar("2", "Renault", "Scenic", 1991);
            carsService.addCar("3", "Hyundai", "Tucson", 1987);
            carsService.addCar("4", "Dacia", "Duster", 2019);
            carsService.addCar("5", "Mercedes", "GLS", 2021);
        }*/

        while (command != EXIT) {
            if (COUNTER == 0) {
                showMenu();
                ++COUNTER;
            }
            command = parseInt(scanner.nextLine());
            try {
                if (command == ADDCAR) {
                    System.out.print("Enter car id: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car make: ");
                    String make = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car model: ");
                    String model = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car year: ");
                    int year = parseInt(scanner.nextLine());
                    System.out.println();
                    carsService.addCar(id, make, model, year);
                } else if (command == REMOVECAR) {
                    System.out.print("Enter id of car to be deleted: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    carsService.deleteCar(id);
                } else if (command == UPDATECAR) {
                    System.out.print("Enter car id: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car make: ");
                    String make = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car model: ");
                    String model = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter car year: ");
                    int year = parseInt(scanner.nextLine());
                    carsService.modifyCar(id, make, model, year);
                } else if (command == SHOWCARBYID) {
                    System.out.print("Enter car id: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    System.out.println(carsService.findCarById(id));
                } else if (command == SHOWALLCARS) {
                    System.out.println(carsService.getAll());
                } else if (command == FILTERCARSBYYEAR) {
                    IRepository<String, Car> carsRepo = new CarRepository();
                    Validator<Car> validator = new CarValidator();
                    for (Car car : carsService.getAll()) {
                        carsRepo.addEntity(car.getId(), car);
                    }
                    System.out.print("Enter filter year: ");
                    int year = parseInt(scanner.nextLine());
                    AbstractFilter<Car> carFilter = new FilterCarsByYear(year);
                    FilteredCarsService filteredCarsService = new FilteredCarsService(carsRepo, validator, carFilter);
                    for (Car car : filteredCarsService.getAll()) {
                        System.out.println(car);
                    }
                } else if (command == ADDRESERVATION) {
                    System.out.print("Enter reservation id: ");
                    String resId = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter reservation date (yyyy-mm-dd): ");
                    String resDate = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter reservation car id: ");
                    String carId = scanner.nextLine();
                    System.out.println();
                    reservationsService.addReservation(resId, LocalDate.parse(resDate), carId);
                } else if (command == REMOVERESERVATION) {
                    System.out.print("Enter the id of the reservation: ");
                    String resId = scanner.nextLine();
                    reservationsService.deleteReservation(resId);
                } else if (command == UPDATERESERVATION) {
                    System.out.print("Enter reservation id: ");
                    String resId = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter the new date (yyyy-mm--dd) of the reservation: ");
                    String resDate = scanner.nextLine();
                    System.out.println();
                    System.out.println("Enter car id: ");
                    String carId = scanner.nextLine();
                    reservationsService.modifyReservation(resId, LocalDate.parse(resDate), carId);
                } else if (command == SHOWRESERVATIONBYID) {
                    System.out.print("Reservation id: ");
                    String resId = scanner.nextLine();
                    System.out.println(reservationsService.findReservationById(resId));
                } else if (command == SHOWALLRESERVATIONS) {
                    System.out.println(reservationsService.getAll());
                } else if (command == GETALLCARSWITHSAMEMAKE) {
                    System.out.print("Enter car make: ");
                    String make = scanner.nextLine();
                    System.out.println();
                    List<String> carIds = carsService.getAllCarsWithSameMake(make);
                    System.out.println("Cars with same make are: " + carIds);
                } else if (command == GETALLCARSWITHSAMEYEAR) {
                    System.out.print("Enter car year: ");
                    int year;
                    year = parseInt(scanner.nextLine());
                    System.out.println();
                    List<String> carIds = carsService.getAllCarsWithSameYear(year);
                    System.out.println("Cars with same year are: " + carIds);
                } else if (command == GETALLCARSRESERVEDAT) {
                    System.out.print("Enter date: ");
                    String resDate = scanner.nextLine();
                    System.out.println();
                    List<String> carIds = reservationsService.getAllCarsReservedAt(LocalDate.parse(resDate));
                    System.out.println("Car ids are: " + carIds);
                } else if (command == GETALLCARSRESERVEDBEFORE) {
                    System.out.print("Enter date: ");
                    String resDate = scanner.nextLine();
                    System.out.println();
                    List<String> carIds = reservationsService.getAllCarsReservedBefore(LocalDate.parse(resDate));
                    System.out.println("Car ids are: " + carIds);
                } else if (command == GETALLCARSRESERVEDAFTER) {
                    System.out.print("Enter date: ");
                    String resDate = scanner.nextLine();
                    System.out.println();
                    List<String> carIds = reservationsService.getAllCarsReservedAfter(LocalDate.parse(resDate));
                    System.out.println("Car ids are: " + carIds);
                } else if (command == EXIT) {
                    break;
                }
            } catch (ValidateException ve) {
                System.out.println("Validation Error: " + ve.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
            showMenu();
        }
        scanner.close();
    }
}