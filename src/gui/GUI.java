package gui;

import domain.Car;
import validator.CarValidator;

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

        IRepository<String, Car> repo = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("settings.properties"));

            String repoType = prop.getProperty("repo_type_cars");
            String repoPath = prop.getProperty("repo_path_cars");

            if (repoType.equals("text"))
                repo = new TextFileCarsRepository(repoPath);
            if (repoType.equals("binary"))
                repo = new BinaryFileCarsRepository(repoPath);
            if (repoType.equals("memory"))
                repo = new CarRepository();
            if (repoType.equals("DB"))
                repo = new DBCarsRepository();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        CarValidator validator = new CarValidator();
        CarsService service = new CarsService(repo, validator);
        Controller controller = new Controller(service);

        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
