package gui;
import domain.Reservation;
import filter.FilterCarsByYear;
import domain.Car;
import filter.AbstractFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import repository.CarRepository;
import repository.IRepository;
import service.CarsService;
import service.FilteredCarsService;
import service.ReservationsService;
import validator.CarValidator;
import validator.Validator;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Controller {
    CarsService carsService;
    ReservationsService reservationsService;

    ObservableList<Car> carsList;
    ObservableList<Reservation> reservationsList;

    @FXML
    private ListView<Car> carsListView;
    @FXML
    private ListView<Car> carsResultsListView;
    @FXML
    private ListView<Reservation> reservationsListView;
    @FXML
    private ListView<Reservation> reservationResultsListView;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField makeTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField yearTextField;

    // 16 in total
    @FXML // 1
    private Button buttonRemoveCar;
    @FXML // 2
    private Button buttonAddCar;
    @FXML // 3
    private Button buttonUpdateCar;
    @FXML // 4
    private Button buttonShowCarById;
    @FXML // 5
    private Button buttonShowAllCars;
    @FXML // 6
    private Button buttonFilterCarsByYear;

    // 1) Add car
    @FXML
    void buttonAddCarHandler(ActionEvent event){
        String id = idTextField.getText();
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int year = Integer.parseInt(yearTextField.getText());
        carsService.addCar(id, make, model, year);
        resetObservableList();
        idTextField.clear();
        makeTextField.clear();
        modelTextField.clear();
        yearTextField.clear();
    }
    // 2) remove car
    @FXML
    void buttonRemoveCarHandler(ActionEvent event){
        Car car = carsListView.getSelectionModel().getSelectedItem();
        carsService.deleteCar(car.getId());
        resetObservableList();
    }
    // 3) update car
    @FXML
    void buttonUpdateCarHandler(ActionEvent event){
        String id = idTextField.getText();
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int year = Integer.parseInt(yearTextField.getText());
        carsService.modifyCar(id, make, model, year);
        resetObservableList();
        idTextField.clear();
        makeTextField.clear();
        modelTextField.clear();
        yearTextField.clear();
    }
    // 4) show car by id
    @FXML
    void buttonShowCarByIdHandler(ActionEvent event){
        String id = idTextField.getText();
        showCarInList(id);
        idTextField.clear();

    }
    void showCarInList(String id) {
        Car car = carsService.findCarById(id);
        if (car != null) {
            ObservableList<Car> carList = FXCollections.observableArrayList(car);
            carsResultsListView.setItems(carList);
        } else {
            // car not found, we clear the list
            carsResultsListView.setItems(FXCollections.observableArrayList());
        }
    }
    // 5) show all cars
    @FXML
    void buttonShowAllCarsHandler(ActionEvent event){
        showAllCarsInList();
    }
    void showAllCarsInList(){
        ArrayList<Car> cars = this.carsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsResultsListView.setItems(carsList);
    }
    // 6) filter cars by year
    @FXML
    void buttonFilterCarsByYearHandler(ActionEvent event){
        showFilteredCarsByYear();
    }
    void showFilteredCarsByYear() {
        CarsService filteredService = new CarsService(carsService);
        IRepository<String, Car> carsRepo = new CarRepository();
        Validator<Car> validator = new CarValidator();
        for (Car car : carsService.getAll()) {
            carsRepo.addEntity(car.getId(), car);
        }
        int year = Integer.parseInt(yearTextField.getText());
        AbstractFilter<Car> carFilter = new FilterCarsByYear(year);
        FilteredCarsService filteredCarsService = new FilteredCarsService(carsRepo, validator, carFilter);
        ArrayList<Car> cars = filteredCarsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsResultsListView.setItems(carsList);
        yearTextField.clear();
    }

    void resetObservableList(){
        ArrayList<Car> cars = this.carsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsListView.setItems(carsList);
    }


    public Controller(CarsService serv, ReservationsService reservService) {
        this.carsService = serv;
        this.reservationsService = reservService;
    }

    public void initialize() {
        ArrayList<Car> cars = this.carsService.getAll();
        ArrayList<Reservation> reservations = this.reservationsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsListView.setItems(carsList);
        reservationsList = FXCollections.observableArrayList(reservations);
        reservationsListView.setItems(reservationsList);
    }
}

/*
ADDCAR                              1 - done
REMOVECAR                           2 - done
UPDATECAR                           3 - done
SHOWCARBYID                         4 - done
SHOWALLCARS                         5 - done
FILTERCARSBYYEAR                    6 - done
ADDRESERVATION                      7
REMOVERESERVATION                   8
UPDATERESERVATION                   9
SHOWRESERVATIONBYID                 10
SHOWALLRESERVATIONS                 11
GETALLCARSWITHSAMEMAKE              12
GETALLCARSWITHSAMEYEAR              13
GETALLCARSRESERVEDAT                14
GETALLCARSRESERVEDBEFORE            15
GETALLCARSRESERVEDAFTER             16
*/
