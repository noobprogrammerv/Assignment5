package filter;
import domain.Reservation;

import java.time.LocalDate;

public class FilterReservationsByDate implements AbstractFilter<Reservation> {
    public LocalDate date;
    public FilterReservationsByDate(LocalDate date){ this.date = date; }
    @Override
    public boolean acceptEntity(Reservation reservation) {
        return this.date.equals(reservation.getDate());
    }
}
