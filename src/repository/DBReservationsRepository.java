package repository;
import domain.Reservation;
import jdk.jshell.spi.ExecutionControl;
import validator.ValidateException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBReservationsRepository implements IRepository<String, Reservation>{
    public static String JDBC_URL = "jdbc:sqlite:data/reservationsDB.sqlite";
    @Override
    public Iterable<Reservation> getAllEntities() {
        List<Reservation> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement returnAllStatement =
                    conn.prepareStatement("SELECT id, date, carid FROM reservations");
            ResultSet resultSet = returnAllStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String dateStr = resultSet.getString("date");
                LocalDate date = LocalDate.parse(dateStr);
                String carId = resultSet.getString("carid");
                Reservation reservation = new Reservation(id, date, carId);
                result.add(reservation);
            }
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void addEntity(String id, Reservation reservation) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement addStatement =
                    conn.prepareStatement("INSERT INTO reservations (id, date, carId) VALUES (?, ?, ?)");
            addStatement.setString(1, reservation.getId());
            addStatement.setString(2, reservation.getDate().toString());
            addStatement.setString(3, reservation.getCarId());

            addStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(String id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement deleteStatement =
                    conn.prepareStatement("DELETE FROM reservations WHERE id = ?");
            deleteStatement.setInt(1, Integer.parseInt(id));
            deleteStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyEntity(String id, Reservation reservation) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement modifyStatement =
                    conn.prepareStatement("UPDATE reservations SET date = ?, carId = ? WHERE id = ?");

            modifyStatement.setDate(1, java.sql.Date.valueOf(reservation.getDate()));
            modifyStatement.setString(2, reservation.getCarId());
            modifyStatement.setString(3, id);

            modifyStatement.executeUpdate();
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservation findEntityById(String s) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement returnStatement =
                    conn.prepareStatement("SELECT id, date, carid FROM reservations WHERE id = ?");
            returnStatement.setString(1, s);
            ResultSet resultSet = returnStatement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                java.sql.Date sqlDate = resultSet.getDate("date");
                LocalDate date = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                String carId = resultSet.getString("carid");
                return new Reservation(id, date, carId);
            } else {
                return null;
            }
        } catch (SQLException | ValidateException e) {
            throw new RuntimeException(e);
        }
    }
}
