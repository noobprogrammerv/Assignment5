/*
package tests;

import domain.Car;
import repository.TextFileCarsRepository;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
//add_duplicateID_throwsException
// pt functii
public class TextFileCarsRepositoryTest {

    private final String filename = "testCarsRepo.txt";

    // method to get the size of an Iterable
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
        TextFileCarsRepository repo = new TextFileCarsRepository(filename);
        Car car1 = new Car("1", "Dacia", "Logan", 2008);
        Car car2 = new Car("2", "Renault", "Clio", 2012);

        // we add cars to the repository and write to file
        repo.addEntity(car1.getId(), car1);
        repo.addEntity(car2.getId(), car2);
        repo.writeToFile();

        // create a new repository instance and read from file
        repo = new TextFileCarsRepository(filename);
        repo.readFromFile();

        // verify that the repository contains the saved cars
        assertEquals(2, getSize(repo.getAllEntities()));
        assertEquals(car1, repo.findEntityById("1"));
        assertEquals(car2, repo.findEntityById("2"));

    }

    @Test
    public void writeToFile_readFromEmptyFile_returnsEmptyRepository() {
        TextFileCarsRepository repo = new TextFileCarsRepository(filename);

        repo.writeToFile();
        repo = new TextFileCarsRepository(filename);
        repo.readFromFile();
        // check if the file is empty
        assertEquals(0, getSize(repo.getAllEntities()));
    }

    @Test
    public void readFromFile_deletedFile_doesNotThrowException() {
        File file = new File(filename);
        file.delete();

        TextFileCarsRepository repo = new TextFileCarsRepository(filename);
        repo.readFromFile();
    }
}
*/
