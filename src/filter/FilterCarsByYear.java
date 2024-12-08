package filter;
import domain.Car;

public class FilterCarsByYear implements AbstractFilter<Car> {
    public int year;
    public FilterCarsByYear(int year) {
        this.year = year;
    }
    @Override
    public boolean acceptEntity(Car car) {
        return car.getYear() >= this.year;
    }
}
