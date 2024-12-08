package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Identifiable<String>, Serializable {
    private String id;
    private LocalDate date;
    private String carId;

    public Reservation(String id, LocalDate date, String carId) {
        this.id = id;
        this.date = date;
        this.carId = carId;
    }

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String newId) { this.id = newId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }
    @Override
    public String toString() {
        return "Reservation (id: " + id + " | date: " + date + " | car id: " + carId + ')';
    }

}

