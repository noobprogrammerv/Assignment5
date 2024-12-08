package service;

import filter.AbstractFilter;
import domain.Reservation;
import repository.IRepository;
import validator.Validator;
import java.util.ArrayList;

public class FilteredReservationsService extends ReservationsService {
    private AbstractFilter<Reservation> filter;
    public FilteredReservationsService(IRepository <String, Reservation> repo, Validator<Reservation> validator, AbstractFilter<Reservation> filter) {
        super(repo, validator);
        this.filter = filter;
    }
    @Override
    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservations = super.getAll();
        ArrayList<Reservation> filteredReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (filter.acceptEntity(reservation)) {
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }
}
