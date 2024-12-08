package repository;

import domain.Reservation;
import java.io.*;
import java.time.LocalDate;


public class TextFileReservationsRepository extends FileRepository<String, Reservation> {
    public TextFileReservationsRepository (String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line=br.readLine()) != null)
            {
                String [] tokens = line.split(",");
                if (tokens.length != 3)
                    continue;
                String id = tokens[0];
                LocalDate date = LocalDate.parse(tokens[1]);
                String carId = tokens[2];
                Reservation reservation = new Reservation(id, date, carId);
                super.addEntity(id, reservation);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))) {
            for (Reservation res : super.getAllEntities()) {
                bw.write(res.getId() + "," + res.getDate() + "," +
                         res.getCarId() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
