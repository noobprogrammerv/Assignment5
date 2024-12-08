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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private ListView<Reservation> reservationsResultsListView;

    // text fields for car
    @FXML
    private TextField carIdTextField;
    @FXML
    private TextField carMakeTextField;
    @FXML
    private TextField carModelTextField;
    @FXML
    private TextField carYearTextField;

    // text fields for reservation
    @FXML
    private TextField resIdTextField;
    @FXML
    private TextField resDateTextField;
    @FXML
    private TextField resCarIdTextField;



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
    @FXML // 7
    private Button buttonAddReservation;
    @FXML // 8
    private Button buttonRemoveReservation;
    @FXML // 9
    private Button buttonUpdateReservation;
    @FXML // 10
    private Button buttonShowReservationById;
    @FXML // 11
    private Button buttonShowAllReservations;
    @FXML // 12
    private Button buttonGetAllCarsWithSameMake;
    @FXML // 13
    private Button buttonGetAllCarsWithSameYear;
    @FXML // 14
    private Button buttonGetAllCarsReservedAt;
    @FXML // 15
    private Button buttonGetAllCarsReservedBefore;
    @FXML // 16
    private Button buttonGetAllCarsReservedAfter;




    // 1) Add car
    @FXML
    void buttonAddCarHandler(ActionEvent event){
        String id = carIdTextField.getText();
        String make = carMakeTextField.getText();
        String model = carModelTextField.getText();
        int year = Integer.parseInt(carYearTextField.getText());
        carsService.addCar(id, make, model, year);
        resetObservableCarList();
        carIdTextField.clear();
        carMakeTextField.clear();
        carModelTextField.clear();
        carYearTextField.clear();
    }
    // 2) remove car - CLICK ON IT
    @FXML
    void buttonRemoveCarHandler(ActionEvent event){
        Car car = carsListView.getSelectionModel().getSelectedItem();
        carsService.deleteCar(car.getId());
        resetObservableCarList();
    }
    // 3) update car
    @FXML
    void buttonUpdateCarHandler(ActionEvent event){
        String id = carIdTextField.getText();
        String make = carMakeTextField.getText();
        String model = carModelTextField.getText();
        int year = Integer.parseInt(carYearTextField.getText());
        carsService.modifyCar(id, make, model, year);
        resetObservableCarList();
        carIdTextField.clear();
        carMakeTextField.clear();
        carModelTextField.clear();
        carYearTextField.clear();
    }
    // 4) show car by id - enter the id
    @FXML
    void buttonShowCarByIdHandler(ActionEvent event){
        String id = carIdTextField.getText();
        showCarInList(id);
        carIdTextField.clear();

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
        int year = Integer.parseInt(carYearTextField.getText());
        AbstractFilter<Car> carFilter = new FilterCarsByYear(year);
        FilteredCarsService filteredCarsService = new FilteredCarsService(carsRepo, validator, carFilter);
        ArrayList<Car> cars = filteredCarsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsResultsListView.setItems(carsList);
        carYearTextField.clear();
    }
    void resetObservableCarList(){
        ArrayList<Car> cars = this.carsService.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsListView.setItems(carsList);
    }

    // 7) Add reservation
    @FXML
    void buttonAddReservationHandler(ActionEvent event){
        String id = resIdTextField.getText();
        LocalDate date = LocalDate.parse(resDateTextField.getText());
        String carId = resCarIdTextField.getText();
        reservationsService.addReservation(id, date, carId);
        resetObservableReservationList();
        resIdTextField.clear();
        resDateTextField.clear();
        resCarIdTextField.clear();
    }
    // 8) remove reservation - CLICK ON IT
    @FXML
    void buttonRemoveReservationHandler(ActionEvent event){
        Reservation reservation = reservationsListView.getSelectionModel().getSelectedItem();
        reservationsService.deleteReservation(reservation.getId());
        resetObservableReservationList();
    }
    // 9) update reservation
    @FXML
    void buttonUpdateReservationHandler(ActionEvent event){
        String id = resIdTextField.getText();
        LocalDate date = LocalDate.parse(resDateTextField.getText());
        String carId = resCarIdTextField.getText();
        reservationsService.modifyReservation(id, date, carId);
        resetObservableReservationList();
        resIdTextField.clear();
        resDateTextField.clear();
        resCarIdTextField.clear();
    }
    // 10) show reservation by id - enter the id
    @FXML
    void buttonShowReservationByIdHandler(ActionEvent event){
        String id = resIdTextField.getText();
        showReservationInList(id);
        resIdTextField.clear();

    }
    void showReservationInList(String id) {
        Reservation reservation = reservationsService.findReservationById(id);
        if (reservation != null) {
            ObservableList<Reservation> reservationList = FXCollections.observableArrayList(reservation);
            reservationsResultsListView.setItems(reservationList);
        } else {
            // reservation not found, we clear the list
            reservationsResultsListView.setItems(FXCollections.observableArrayList());
        }
    }
    // 11) show all reservations
    @FXML
    void buttonShowAllReservationsHandler(ActionEvent event){
        showAllReservationsInList();
    }
    void showAllReservationsInList(){
        ArrayList<Reservation> reservations = this.reservationsService.getAll();
        reservationsList = FXCollections.observableArrayList(reservations);
        reservationsResultsListView.setItems(reservationsList);
    }

    void resetObservableReservationList(){
        ArrayList<Reservation> reservations = this.reservationsService.getAll();
        reservationsList = FXCollections.observableArrayList(reservations);
        reservationsListView.setItems(reservationsList);
    }
    // 12) get all cars with same make
    @FXML
    void buttonGetAllCarsWithSameMakeHandler(ActionEvent event) {
        CarsService newCarsService = new CarsService(this.carsService);
        List<Car> cars = newCarsService.getAllCarsWithSameMakeGUI(carMakeTextField.getText());;
        carsList = FXCollections.observableArrayList(cars);
        carsResultsListView.setItems(carsList);
        carMakeTextField.clear();
    }
    // 13) get all cars with same year
    @FXML
    void buttonGetAllCarsWithSameYearHandler(ActionEvent event) {
        CarsService newCarsService = new CarsService(this.carsService);
        List<Car> cars = newCarsService.getAllCarsWithSameYearGUI(Integer.parseInt(carYearTextField.getText()));;
        carsList = FXCollections.observableArrayList(cars);
        carsResultsListView.setItems(carsList);
        carMakeTextField.clear();
    }
    // 14 get all cars reserved at
    @FXML
    void buttonGetAllCarsReservedAtHandler(ActionEvent event) {
        LocalDate date = LocalDate.parse(resDateTextField.getText());
        List<Reservation> res = reservationsService.getAllReservationsReservedAt(date);
        reservationsList = FXCollections.observableArrayList(res);
        reservationsResultsListView.setItems(reservationsList);
        resDateTextField.clear();
    }
    // 15 get all cars reserved before
    @FXML
    void buttonGetAllCarsReservedBeforeHandler(ActionEvent event) {
        LocalDate date = LocalDate.parse(resDateTextField.getText());
        List<Reservation> res = reservationsService.getAllReservationsReservedBefore(date);
        reservationsList = FXCollections.observableArrayList(res);
        reservationsResultsListView.setItems(reservationsList);
        resDateTextField.clear();
    }
    // get all cars reserved after
    @FXML
    void buttonGetAllCarsReservedAfterHandler(ActionEvent event) {
        LocalDate date = LocalDate.parse(resDateTextField.getText());
        List<Reservation> res = reservationsService.getAllReservationsReservedAfter(date);
        reservationsList = FXCollections.observableArrayList(res);
        reservationsResultsListView.setItems(reservationsList);
        resDateTextField.clear();
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
ADDRESERVATION                      7 - done
REMOVERESERVATION                   8 - done
UPDATERESERVATION                   9 - done
SHOWRESERVATIONBYID                 10 - done
SHOWALLRESERVATIONS                 11 - done
GETALLCARSWITHSAMEMAKE              12 - done
GETALLCARSWITHSAMEYEAR              13 - done
GETALLCARSRESERVEDAT                14 - done
GETALLCARSRESERVEDBEFORE            15 - done
GETALLCARSRESERVEDAFTER             16 - done
*/
