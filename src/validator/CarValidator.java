package validator;

import domain.Car;

public class CarValidator implements Validator<Car> {

    @Override
    public void validate(Car car) throws ValidateException {
        String errors = "";

        if (car.getId().isEmpty()) {
            errors += "Car ID cannot be empty!\n";
        }
        if (car.getMake().isEmpty()) {
            errors += "Car make cannot be empty!\n";
        }
        if (car.getModel().isEmpty()) {
            errors += "Car model cannot be empty!\n";
        }
        if (car.getYear() <= 0) {
            errors += "Car year must be positive!\n";
        }

        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }

}
