package repository;

import domain.Car;
import java.io.*;


public class TextFileCarsRepository extends FileRepository<String, Car> {
    public TextFileCarsRepository (String filename) { super(filename); }

    @Override
    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line=br.readLine()) != null) {
                String [] tokens = line.split(",");
                if (tokens.length != 4)
                    continue;
                String id = tokens[0];
                String make = tokens[1];
                String model = tokens[2];
                int year = Integer.parseInt(tokens[3]);
                Car car = new Car(id, make, model, year);
                super.addEntity(id, car);
            }
        } catch (EOFException e) { // If the file is empty
            return;
        } catch (FileNotFoundException e) { // File doesn t exist
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))) {
            for (Car car : super.getAllEntities()) {
                bw.write(car.getId() + "," + car.getMake() + "," +
                        car.getModel() + "," + car.getYear() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
