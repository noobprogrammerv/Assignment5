package validator;

import domain.Reservation;
import java.time.LocalDate;


public class ReservationValidator implements Validator<Reservation> {

        @Override
        public void validate(Reservation reservation) throws ValidateException {
            String errors = "";

            if (reservation.getId().isEmpty()) {
                errors += "Reservation ID cannot be empty!\n";
            }
            if (reservation.getDate().isBefore(LocalDate.now())) {
                errors += "Reservation date cannot be in the past!\n";
            }
            if (reservation.getCarId().isEmpty()) {
                errors += "Reservation car ID cannot be empty!\n";
            }

            if (!errors.isEmpty()) {
                throw new ValidateException(errors);
            }
        }
    }

