/*
package tests;

import domain.Car;
import repository.BinaryFileCarsRepository;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryFileCarsRepositoryTest {

    private String filename = "testCarsRepo.bin";

    // we get the size of an Iterable
    private int getSize(Iterable<Car> iterable) {
        int numberOfCars = 0;
        Iterator<Car> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            numberOfCars++;
        }
        return numberOfCars;
    }

    @Test
    public void writeToFileAndReadFromFile_savesAndReadsCars_correctlyLoadsSavedCars() {
        BinaryFileCarsRepository repo = new BinaryFileCarsRepository(filename);
        Car car1 = new Car("1", "Dacia", "Logan", 2008);
        Car car2 = new Car("2", "Renault", "Clio", 2012);

        // we add cars to the repository and write to file
        repo.addEntity(car1.getId(), car1);
        repo.addEntity(car2.getId(), car2);
        repo.writeToFile();

        // create a new repository instance and read from file
        repo = new BinaryFileCarsRepository(filename);
        repo.readFromFile();

        // we check if the repository contains the saved cars
        assertEquals(2, getSize(repo.getAllEntities()));
        assertEquals(car1, repo.findEntityById("1"));
        assertEquals(car2, repo.findEntityById("2"));
    }

    @Test
    public void readFromFile_fileDoesNotExist_doesNotThrowException() {
        // we delete the file
        File file = new File(filename);
        file.delete();
        // we read from inexisting file
        BinaryFileCarsRepository repo = new BinaryFileCarsRepository(filename);
        repo.readFromFile();
    }
}
*/
