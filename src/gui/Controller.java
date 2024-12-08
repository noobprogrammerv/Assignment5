package gui;

import domain.Car;
import domain.Reservation;
import domain.Identifiable;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.CarsService;

import java.util.ArrayList;

public class Controller {
    CarsService serv;
    ObservableList<Car> carsList;
    ObservableList<Car> resultsList;

    @FXML
    private ListView<Car> carsListView;
    @FXML
    private ListView<Car> resultsListView;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField makeTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField yearTextField;

    @FXML
    private Button buttonRemoveCar;
    @FXML
    private Button buttonAddCar;
    @FXML
    private Button buttonUpdateCar;
    @FXML
    private Button buttonShowCarById;

    // 1) Add car
    @FXML
    void buttonAddCarHandler(ActionEvent event){
        String id = idTextField.getText();
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int year = Integer.parseInt(yearTextField.getText());
        serv.addCar(id, make, model, year);
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
        serv.deleteCar(car.getId());
        resetObservableList();
    }
    // 3) update car
    @FXML
    void buttonUpdateCarHandler(ActionEvent event){
        String id = idTextField.getText();
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int year = Integer.parseInt(yearTextField.getText());
        serv.modifyCar(id, make, model, year);
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

    }
    void showCarInList(String id) {
        Car car = serv.findCarById(id);
        if (car != null) {
            ObservableList<Car> carList = FXCollections.observableArrayList(car);
            resultsListView.setItems(carList);
        } else {
            // car not found, we clear the list
            resultsListView.setItems(FXCollections.observableArrayList());
        }
    }
    // 5) show all cars
    void showAllCarsInList(){
        ArrayList<Car> cars = this.serv.getAll();
        carsList = FXCollections.observableArrayList(cars);
        resultsListView.setItems(carsList);
    }

    void resetObservableList(){
        ArrayList<Car> cars = this.serv.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsListView.setItems(carsList);
    }


    public Controller(CarsService serv) {
        this.serv = serv;
    }

    public void initialize() {
        ArrayList<Car> cars = this.serv.getAll();
        carsList = FXCollections.observableArrayList(cars);
        carsListView.setItems(carsList);
    }
}

/*
ADDCAR                              1 - done
REMOVECAR                           2 - done
UPDATECAR                           3 - done
SHOWCARBYID                         4 - done
SHOWALLCARS                         5
FILTERCARSBYYEAR                    6
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
