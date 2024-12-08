package repository;

import domain.Reservation;
import java.io.*;

public class BinaryFileReservationsRepository extends FileRepository <String, Reservation> {
    public BinaryFileReservationsRepository(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filename))) {
            this.elements = (java.util.HashMap<String, Reservation>) ois.readObject();
        } catch (EOFException e) {
            return;
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            oos.writeObject(this.elements);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
