/*
package repository;

import domain.Car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class XMLFileCarsRepository extends FileRepository<String, Car> {
    // Use XmlMapper instead of ObjectMapper
    private final XmlMapper xmlMapper = new XmlMapper();

    public XMLFileCarsRepository(String filename) {
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
            // Read the entire XML content into a List<Car>
            List<Car> carsList = xmlMapper.readValue(br, new TypeReference<List<Car>>() {});
            // Populate the repository with the cars
            for (Car car : carsList) {
                super.addEntity(car.getId(), car);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from XML file: " + e.getMessage(), e);
        }
    }

    @Override
    public void writeToFile() {
        List<Car> carsList = new ArrayList<>();
        for (Car car : super.getAllEntities()) {
            carsList.add(car);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))) {
            // Write the list as XML
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(bw, carsList);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to XML file: " + e.getMessage(), e);
        }
    }

}
*/
