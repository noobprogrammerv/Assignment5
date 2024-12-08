package repository;
import domain.Car;
import jdk.jshell.spi.ExecutionControl;
import validator.ValidateException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCarsRepository implements IRepository<String, Car>{
    public static String JDBC_URL = "jdbc:sqlite:data/carsDB.sqlite";
    @Override
    public Iterable<Car> getAllEntities() {
        List<Car> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement returnAllStatement =
                    conn.prepareStatement("SELECT * from cars");
            ResultSet resultSet = returnAllStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year = resultSet.getInt(4);
                Car car = new Car(id, make, model, year);
                result.add(car);
            }
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void addEntity(String id, Car car) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement addStatement =
                    conn.prepareStatement("INSERT INTO cars(id, make, model, year) " +
                            "VALUES (?, ?, ?, ?)");
            addStatement.setString(1, car.getId());
            addStatement.setString(2, car.getMake());
            addStatement.setString(3, car.getModel());
            addStatement.setInt(4, car.getYear());
            addStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(String id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement deleteStatement =
                    conn.prepareStatement("DELETE FROM cars WHERE id = ?");
            deleteStatement.setInt(1, Integer.parseInt(id));
            deleteStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyEntity(String id, Car car) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement modifyStatement =
                    conn.prepareStatement("UPDATE cars SET make = ?, model = ?, year = ? WHERE id = ?");
            modifyStatement.setString(1, car.getMake());
            modifyStatement.setString(2, car.getModel());
            modifyStatement.setInt(3, car.getYear());
            modifyStatement.setString(4, id);
            modifyStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car findEntityById(String s) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement returnAllStatement =
                    conn.prepareStatement("SELECT * from cars WHERE id = ?");
            returnAllStatement.setString(1,s);
            ResultSet resultSet = returnAllStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year = resultSet.getInt(4);
                Car car = new Car(id, make, model, year);
                return car;
            } else {
                return null;
            }
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }
}
