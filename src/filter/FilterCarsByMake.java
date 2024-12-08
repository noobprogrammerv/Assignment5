package filter;
import domain.Car;

public class FilterCarsByMake implements AbstractFilter<Car> {
    public String carMake;
    public FilterCarsByMake(String carMake) { this.carMake = carMake; }
    @Override
    public boolean acceptEntity(Car car) {
        return car.getMake().equals(carMake);
    }
}
