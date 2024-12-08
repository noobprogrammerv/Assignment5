package repository;

import domain.Car;
import java.io.*;

public class  BinaryFileCarsRepository extends FileRepository <String, Car> {

    public BinaryFileCarsRepository(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filename))) {
            this.elements = (java.util.HashMap<String, Car>) ois.readObject();
        } catch (EOFException e) {//if the file is empty
            return;
        } catch (FileNotFoundException e) {//file does not exist
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            oos.writeObject(this.elements);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
