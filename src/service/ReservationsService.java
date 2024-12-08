package service;

import domain.Car;
import domain.Reservation;
import repository.IRepository;
import validator.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import validator.ValidateException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReservationsService {
    private final IRepository<String, Reservation> reservationsRepo;
    private final Validator<Reservation> reservationValidator;
    public ReservationsService(IRepository<String, Reservation> repo, Validator<Reservation> validator) {
        this.reservationsRepo = repo;
        this.reservationValidator = validator;
    }

    public void addReservation(String id, LocalDate date, String carId) throws ValidateException {
        Reservation reservation = new Reservation(id, date, carId);
        // We perform the validation
        reservationValidator.validate(reservation);
        reservationsRepo.addEntity(reservation.getId(), reservation);
    }

    public void deleteReservation(String id) {
        reservationsRepo.deleteEntity(id);
    }

    public Reservation findReservationById(String id) {
        return reservationsRepo.findEntityById(id);
    }

    public void modifyReservation(String id, LocalDate date, String carId) throws ValidateException {
        Reservation reservation = new Reservation(id, date, carId);
        // We perform the validation
        reservationValidator.validate(reservation);
        reservationsRepo.modifyEntity(reservation.getId(), reservation);
    }

    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationsRepo.getAllEntities()) {
            reservations.add(reservation);
        }
        return reservations;
    }
    // 1) Get all reservations reserved at certain date
    public List<String> getAllCarsReservedAt(LocalDate reservedDate ) {
        return StreamSupport.stream(reservationsRepo.getAllEntities().spliterator(), false)
                .filter(r -> r.getDate().equals(reservedDate))
                .map(r -> r.getCarId()).distinct()
                .collect(Collectors.toList());

    }
    // FOR GUI
    public List<Reservation> getAllReservationsReservedAt(LocalDate reservedDate ) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationsRepo.getAllEntities()) {
            if (reservation.getDate().equals(reservedDate)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // 2) Get all reservations reserved before a certain date
    public List<String> getAllCarsReservedBefore(LocalDate reservedDate) {
        return StreamSupport.stream(reservationsRepo.getAllEntities().spliterator(), false)
                .filter(reservation -> reservation.getDate().isBefore(reservedDate))
                .map(r -> r.getCarId()).distinct()
                .collect(Collectors.toList());
    }
    // FOR GUI
    public List<Reservation> getAllReservationsReservedBefore(LocalDate reservedDate ) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationsRepo.getAllEntities()) {
            if (reservation.getDate().isBefore(reservedDate)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // 3) Get all reservations reserved after a certain date
    public List<String> getAllCarsReservedAfter(LocalDate reservedDate) {
        return StreamSupport.stream(reservationsRepo.getAllEntities().spliterator(), false)
                .filter(reservation -> reservation.getDate().isAfter(reservedDate))
                .map(r -> r.getCarId()).distinct()
                .collect(Collectors.toList());
    }
    // FOR GUI
    public List<Reservation> getAllReservationsReservedAfter(LocalDate reservedDate ) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationsRepo.getAllEntities()) {
            if (reservation.getDate().isAfter(reservedDate)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }
    public IRepository<String, Reservation> getRepo() {
        return reservationsRepo;
    }
}
