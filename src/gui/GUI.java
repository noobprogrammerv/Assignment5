package gui;

import domain.Car;
import domain.Reservation;
import service.ReservationsService;
import validator.CarValidator;
import validator.ReservationValidator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import repository.*;
import service.CarsService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GUI extends Application {

    HBox mainLayout;
    ListView<Car> listView;
    ObservableList<Car> listOfCars;
    GridPane righLayout;
    Label idLabel, makeLabel, modelLabel, yearLabel;
    TextField idTextField, makeTextField, modelTextField, yearTextField;
    Button addButton;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));

        IRepository<String, Car> carsRepo = null;
        IRepository<String, Reservation> reservationsRepo = null;

        Properties prop = new Properties();
        try {
            prop.load(new FileReader("settings.properties"));

            String carsRepoType = prop.getProperty("repo_type_cars");
            String carsRepoPath = prop.getProperty("repo_path_cars");
            String reservationsRepoType = prop.getProperty("repo_type_reservations");
            String reservationsRepoPath = prop.getProperty("repo_path_reservations");


            if (carsRepoType.equals("text"))
                carsRepo = new TextFileCarsRepository(carsRepoPath);
            if (carsRepoType.equals("binary"))
                carsRepo = new BinaryFileCarsRepository(carsRepoPath);
            if (carsRepoType.equals("memory"))
                carsRepo = new CarRepository();
            if (carsRepoType.equals("DB"))
                carsRepo = new DBCarsRepository();

            if (reservationsRepoType.equals("text"))
                reservationsRepo = new TextFileReservationsRepository(reservationsRepoPath);
            if (reservationsRepoType.equals("binary"))
                reservationsRepo = new BinaryFileReservationsRepository(reservationsRepoPath);
            if (reservationsRepoType.equals("memory"))
                reservationsRepo = new ReservationRepository();
            if (reservationsRepoType.equals("DB"))
                reservationsRepo = new DBReservationsRepository();



        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        CarValidator validator = new CarValidator();
        ReservationValidator reservationValidator = new ReservationValidator();
        CarsService carsService = new CarsService(carsRepo, validator);
        ReservationsService reservationsService = new ReservationsService(reservationsRepo, reservationValidator);
        Controller controller = new Controller(carsService, reservationsService);

        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
