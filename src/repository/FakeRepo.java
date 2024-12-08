package repository;

import domain.Car;
import java.util.HashMap;

public class FakeRepo implements IRepository<String, Car> {
    private HashMap<String, Car> elements = new HashMap<>();

    @Override
    public void addEntity(String id, Car car) {
        elements.put(id, car);
    }

    @Override
    public void deleteEntity(String id) {
        elements.remove(id);
    }

    @Override
    public void modifyEntity(String id, Car car) {
        elements.put(id, car);
    }

    @Override
    public Car findEntityById(String id) {
        return elements.get(id);
    }

    @Override
    public Iterable<Car> getAllEntities() {
        return elements.values();
    }


}
