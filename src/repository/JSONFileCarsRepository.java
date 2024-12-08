/*
package repository;

import domain.Car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//JSON JACKSON tutorial
public class JSONFileCarsRepository extends FileRepository<String, Car> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JSONFileCarsRepository(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        File file = new File(this.filename);
        if (!file.exists()) {
            // If the file doesn't exist, there's nothing to read
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read the entire JSON content into a Map
            List<Car> carsList = objectMapper.readValue(br, new TypeReference<List<Car>>() {});
            // Populate the repository with the cars
            for (Car car : carsList) {
                super.addEntity(car.getId(), car);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from JSON file: " + e.getMessage(), e);
        }
    }

    @Override
    public void writeToFile() {
        List<Car> carsList = new ArrayList<>();
        for (Car car : super.getAllEntities()) {
            carsList.add(car);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))) {
            // Write the list as a JSON array
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(bw, carsList);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to JSON file: " + e.getMessage(), e);
        }
    }

}
*/
