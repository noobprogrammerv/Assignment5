/*
package tests;

import org.junit.jupiter.api.Test;
import domain.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    @Test
    public void constructor_defaultConstructor_setsDefaultValues() {
        Car car = new Car();
        assertEquals("", car.getId());
        assertEquals("", car.getMake());
        assertEquals("", car.getModel());
        assertEquals(-1, car.getYear());
    }


    @Test
    public void constructor_parameterizedConstructor_setsFieldsCorrectly() {
        Car car1 = new Car("1", "ARO", "vechi", 1992);
        assertEquals("1", car1.getId());
        assertEquals("ARO", car1.getMake());
        assertEquals("vechi", car1.getModel());
        assertEquals(1992, car1.getYear());
    }
    @Test
    public void setMethods_updateFields_correctlyUpdatesFields() {
        Car car1 = new Car("1", "ARO", "vechi", 1992);
        car1.setId("99");
        car1.setMake("VW");
        car1.setModel("Tiguan");
        car1.setYear(2013);
        assertEquals("99", car1.getId());
        assertEquals("VW", car1.getMake());
        assertEquals("Tiguan", car1.getModel());
        assertEquals(2013, car1.getYear());
    }

    @Test
    public void toString_returnsStringRepresentation_correctFormat() {
        Car car1 = new Car("1", "ARO", "vechi", 1992);
        String expectedString = "Car: (id: 1, make: ARO, model: vechi, year: 1992)";
        assertEquals(expectedString, car1.toString());
    }

    @Test
    public void equals_sameAttributes_returnsTrue() {
        Car car1 = new Car("1", "ARO", "vechi", 1992);
        Car car2 = new Car("1", "ARO", "vechi", 1992);
        Car car3 = new Car("2", "Ford", "Focus", 2010);
        assertEquals(car1, car2);
    }

    @Test
    public void hashCode_sameAttributes_returnsSameHashCode() {
        Car car1 = new Car("1", "ARO", "vechi", 1992);
        Car car2 = new Car("1", "ARO", "vechi", 1992);
        assertEquals(car1.hashCode(), car2.hashCode());
    }

}


*/
