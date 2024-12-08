/*
package tests;

import org.junit.jupiter.api.Test;
import domain.Car;
import filter.FilterCarsByYear;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCarsByYearTest {
    @Test
    public void acceptEntity_yearGreaterThanOrEqualToFilterYear_returnsTrue() {
        FilterCarsByYear filter = new FilterCarsByYear(2015);

        Car car1 = new Car("1", "Dacia", "Logan", 2015); // Year is above the filter year
        Car car2 = new Car("2", "Mercedes", "GLE", 2019);  // Year is equal to the filter year
        assertEquals(true, filter.acceptEntity(car1));
        assertEquals(true, filter.acceptEntity(car2));
    }

    @Test
    public void constructor_filterYear_setsCorrectFilterYear() {
        FilterCarsByYear filter = new FilterCarsByYear(2020);
        assertEquals(2020, filter.year);
    }

}
*/
