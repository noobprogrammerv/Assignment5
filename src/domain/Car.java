package domain;
import java.io.Serializable;
import java.util.Objects;

public class Car implements Identifiable<String>, Serializable {
    private String id;
    private String make;
    private String model;
    private Integer year;

    public Car() {
       /* id = "";
        make = "";
        model = "";
        year = -1;*/
    }
    public Car (String id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String newId) {
        id = newId;
    }
    @Override
    public String toString() {
        return "Car: (id: " + id + ", make: " + make + ", model: " + model + ", year: " + year + ')';
    }
    @Override
    public int hashCode() { return Objects.hash(id, make, model, year); }

   @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;
        return id.equals(car.getId()) && make.equals(car.getMake()) && model.equals(car.getModel())
                && year == car.getYear();
    }



}
