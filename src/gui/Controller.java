package gui;

import domain.Car;
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

    @FXML
    private ListView<Car> carsListView;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField makeTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private Button removeButton;


    @FXML
    private Button buttonAdd;

    @FXML
    void buttonAddHandler(ActionEvent event){
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

    @FXML
    void buttonRemoveHandler(ActionEvent event){
        Car car = carsListView.getSelectionModel().getSelectedItem();
        serv.deleteCar(car.getId());
        resetObservableList();
    }

    @FXML
    void buttonUpdateHandler(ActionEvent event){
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
        /// show car by id
      // show car by id
    /*void buttonShowCarByIdHandler(ActionEvent event){

    }*/

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